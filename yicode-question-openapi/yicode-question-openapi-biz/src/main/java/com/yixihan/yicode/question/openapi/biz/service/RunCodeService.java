package com.yixihan.yicode.question.openapi.biz.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.yixihan.yicode.common.constant.MessageConstant;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.enums.question.CodeAnswerEnums;
import com.yixihan.yicode.common.enums.question.CodeTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.question.api.dto.request.label.ModifyLabelUserDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.AddUserDailyQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCaseDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDailyDtoResult;
import com.yixihan.yicode.question.openapi.api.prop.CodeJudgeProp;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CodeRunVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.label.LabelQuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.label.LabelUserFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionAnswerFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionCaseFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionDailyUserFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.run.CodeRunFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.user.extra.UserLanguageFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.question.SseEmitterService;
import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLanguageDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.yixihan.yicode.common.constant.RedisKeyConstant.DAILY_QUESTION_KEY;

/**
 * 代码运行 服务类
 *
 * @author yixihan
 * @date 2023/1/28 16:03
 */
@Slf4j
@Service
public class RunCodeService {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    @Resource
    private SseEmitterService sseEmitterService;
    
    @Resource
    private CodeRunFeignClient codeRunFeignClient;
    
    @Resource
    private QuestionCaseFeignClient questionCaseFeignClient;
    
    @Resource
    private QuestionAnswerFeignClient questionAnswerFeignClient;
    
    @Resource
    private QuestionDailyUserFeignClient questionDailyUserFeignClient;
    
    @Resource
    private UserLanguageFeignClient userLanguageFeignClient;
    
    @Resource
    private LabelQuestionFeignClient labelQuestionFeignClient;
    
    @Resource
    private LabelUserFeignClient labelUserFeignClient;
    
    @Resource
    private CodeJudgeProp codeJudgeProp;
    
    private Long dailyQuestionId;
    
    @RabbitListener(queues = MessageConstant.TASK_TEST_QUEUE_NAME)
    public void test(Message message, Channel channel) {
        try {
            // 获取 req
            CodeReq req = JSONUtil.toBean (new String (message.getBody ()), CodeReq.class);
            
            // 构建请求 body
            CodeRunDtoReq dtoReq = new CodeRunDtoReq ();
            dtoReq.setCode (req.getCode ());
            dtoReq.setCodeType (req.getType ());
            dtoReq.setParamList (CollUtil.newArrayList (req.getParam ()));
            
            // 运行代码
            CodeRunDtoResult result = codeRunFeignClient.runCode (dtoReq).getResult ();
            log.info("自测代码, 用户 id : {}, 运行结果 : {}", req.getUserId(), result);
            // 推送给前端
            CodeRunVO vo = BeanUtil.toBean (result, CodeRunVO.class);
            vo.setUserId(req.getUserId());
            sseEmitterService.sendMsgToClient (vo);
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (Exception e) {
            log.error ("出现异常", e);
            try {
                channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
            } catch (IOException ex) {
                log.error ("出现异常", e);
                throw new BizException(BizCodeEnum.CODE_RUN_ERR);
            }
            throw new BizException (BizCodeEnum.CODE_RUN_ERR);
        }
    }
    
    @RabbitListener(queues = MessageConstant.TASK_COMMIT_QUEUE_NAME)
    public void commit(Message message, Channel channel) {
        CodeAnswerEnums status;
        CodeRunDtoResult result = null;
        // 获取 req
        CodeReq req = JSONUtil.toBean (new String (message.getBody ()), CodeReq.class);
        try {
            // 获取测试用例
            List<QuestionCaseDtoResult> questionCase = questionCaseFeignClient.allQuestionCaseList (req.getQuestionId ()).getResult ();
    
            // 构建请求 body
            CodeRunDtoReq dtoReq = new CodeRunDtoReq ();
            dtoReq.setCode (req.getCode ());
            dtoReq.setCodeType (req.getType ());
            dtoReq.setParamList (questionCase.stream ()
                    .map (QuestionCaseDtoResult::getCaseParams).collect (Collectors.toList ()));
            log.info("提交代码, 形参 : {}", dtoReq.getParamList());
            // 运行代码
            result = codeRunFeignClient.runCode (dtoReq).getResult ();
            log.info("提交代码, 用户 id : {}, 运行结果 : {}", req.getUserId(), result);
            // 测评代码
            status = judgeCode (result, questionCase);
            log.info("提交代码, 用户 id : {}, 运行结果 : {}, 测评结果 : {}", req.getUserId(), result, status);
            // 推送给前端
            CodeRunVO vo = BeanUtil.toBean (result, CodeRunVO.class);
            vo.setStatus (status);
            vo.setUserId(req.getUserId());
            sseEmitterService.sendMsgToClient (vo);
            
            // 保存到数据库
            saveQuestionAnswer (req, result, status);
            
            // 测评正确
            if (status.equals (CodeAnswerEnums.AC)) {
                // 如果做的是每日一题, 则保存到数据库中
                if (req.getQuestionId ().equals (dailyQuestionId)) {
                    modifyUserDailyQuestion (req);
                }
                // 更新用户标签
                modifyUserLabel (req);
                // 更新用户语言
                modifyUserLanguage (req);
            }
            
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (RetryableException e) {
            // 超时
            status = CodeAnswerEnums.TLE;
            // 推送给前端
            CodeRunVO vo = BeanUtil.toBean (result, CodeRunVO.class);
            vo.setStatus (status);
            vo.setUserId(req.getUserId());
            sseEmitterService.sendMsgToClient (vo);
            // 保存到数据库
            saveQuestionAnswer (req, result, status);
            try {
                channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
            } catch (IOException ex) {
                throw new BizException (BizCodeEnum.CODE_RUN_ERR);
            }
        } catch (Exception e) {
            log.error ("出现错误", e);
            // 系统内部错误
            status = CodeAnswerEnums.SE;
            // 推送给前端
            CodeRunVO vo = BeanUtil.toBean (result, CodeRunVO.class);
            vo.setStatus (status);
            vo.setUserId(req.getUserId());
            sseEmitterService.sendMsgToClient (vo);
            // 保存到数据库
            saveQuestionAnswer (req, result, status);
            try {
                channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
            } catch (IOException ex) {
                throw new BizException (BizCodeEnum.CODE_RUN_ERR);
            }
        }
    }
    
    /**
     * 测评代码
     *
     * @param result 代码运行结果
     * @param questionCase 测试用例
     */
    private CodeAnswerEnums judgeCode (CodeRunDtoResult result, List<QuestionCaseDtoResult> questionCase) {
        // 编译失败
        if (Boolean.FALSE.equals (result.getCompile ())) {
            return CodeAnswerEnums.CE;
        }
    
        // 运行失败
        if (Boolean.FALSE.equals (result.getRun ())) {
            return CodeAnswerEnums.RE;
        }
    
        // 编译, 运行成功
        List<String> runAnsList = result.getAnsList ();
        List<String> paramAnsList = questionCase.stream ()
                .map (QuestionCaseDtoResult::getCaseAnswer)
                .collect(Collectors.toList());
    
        // 长度不一
        if (paramAnsList.size () != runAnsList.size ()) {
            return CodeAnswerEnums.WA;
        }
    
        // 逐个对比
        int len = 0;
        for (int i = 0; i < paramAnsList.size (); i++) {
            // 累计输出长度
            len += runAnsList.get (i).length ();
            
            // 长度超限
            if (len >= codeJudgeProp.getMaxOutputLen ()) {
                return CodeAnswerEnums.OL;
            }
            // 输出不等
            if (!StrUtil.equals (paramAnsList.get (i), runAnsList.get (i))) {
                String replaceStr = runAnsList.get (i).replaceAll ("\\s*", "");
                if (StrUtil.equals (paramAnsList.get (i), replaceStr)) {
                    // 格式错误
                    return CodeAnswerEnums.PE;
                } else {
                    // 输出不等
                    return CodeAnswerEnums.WA;
                }
            }
        }
        
        return CodeAnswerEnums.AC;
    }
    
    /**
     * 将代码运行结果保存到数据库中
     *
     * @param req 代码提交参数
     * @param result 代码运行结果
     * @param status 代码测评状态
     */
    private void saveQuestionAnswer (@NotNull CodeReq req,
                                     @Nullable CodeRunDtoResult result,
                                     @NotNull CodeAnswerEnums status) {
        AddQuestionAnswerDtoReq dtoReq = new AddQuestionAnswerDtoReq ();
        dtoReq.setUserId (req.getUserId ());
        dtoReq.setQuestionId (req.getQuestionId ());
        dtoReq.setAnswerCode (req.getCode ());
        dtoReq.setAnswerType (req.getType ());
        dtoReq.setAnswerFlag (status.getAnswer ());
        if (result != null) {
            dtoReq.setAnswerTimeConsume (Math.toIntExact (result.getUseTime ()));
            dtoReq.setAnswerMemoryConsume (result.getUseMemory ());
        }
        
        questionAnswerFeignClient.addQuestionAnswer (dtoReq);
    }
    
    /**
     * 更新用户每日一题做题情况
     *
     * @param req 请求参数
     */
    private void modifyUserDailyQuestion (CodeReq req) {
        AddUserDailyQuestionDtoReq dailyDtoReq = new AddUserDailyQuestionDtoReq ();
        dailyDtoReq.setQuestionId (req.getQuestionId ());
        dailyDtoReq.setUserId (req.getUserId ());
        questionDailyUserFeignClient.addUserDailyQuestion (dailyDtoReq);
    }
    
    /**
     * 更新用户标签
     *
     * @param req 请求参数
     */
    private void modifyUserLabel (CodeReq req) {
        // 获取问题标签
        List<LabelDtoResult> questionLableList = labelQuestionFeignClient.questionLabelDetail (req.getQuestionId ()).getResult ();
        // 获取用户标签
        List<LabelDtoResult> userLableList = labelUserFeignClient.userLabelDetail (req.getUserId ()).getResult ();
    
        // 去重
        Set<Long> labelList = questionLableList.stream ()
                .map (LabelDtoResult::getLabelId)
                .filter (labelId ->
                        userLableList.stream ().noneMatch (o -> labelId.equals (o.getLabelId ())))
                .collect (Collectors.toSet ());
    
        ModifyLabelUserDtoReq dtoReq = new ModifyLabelUserDtoReq ();
        dtoReq.setUserId (req.getUserId ());
        dtoReq.setLabelId (new ArrayList<> (labelList));
        labelUserFeignClient.addUserLabel (dtoReq);
    }
    
    /**
     * 更新用户语言
     *
     * @param req 请求参数
     */
    private void modifyUserLanguage (CodeReq req) {
        String language = CodeTypeEnums.valueOf (req.getType ()).getType ();
        // 获取用于语言列表
        List<UserLanguageDtoResult> userLanguage = userLanguageFeignClient.getUserLanguage (req.getUserId ()).getResult ();
    
        // 构建请求 body
        ModifyUserLanguageDtoReq dtoReq = new ModifyUserLanguageDtoReq ();
        dtoReq.setUserId (req.getUserId ());
        dtoReq.setLanguage (language);
        
        // 用户没有此语言, 添加语言
        if (userLanguage.stream ().noneMatch (o -> o.getLanguage ().equals (language))) {
            userLanguageFeignClient.addUserLanguage (dtoReq);
            return;
        }
        // 用户有此语言, 更新语言
        UserLanguageDtoResult userLanguageDtoResult = userLanguage.stream ()
                .filter (o -> o.getLanguage ().equals (language))
                .findFirst ()
                .orElse (new UserLanguageDtoResult ());
        dtoReq.setDealCount (userLanguageDtoResult.getDealCount () + 1);
        userLanguageFeignClient.modifyUserLanguage (dtoReq);
    }
    
    /**
     * 获取每日一题问题 ID
     */
    @PostConstruct
    @Scheduled(cron = "30 0 0 * * ?")
    public void getDailyQuestion () {
        // 生成当月 redis key
        Date nowTime = new Date ();
        String yearMonth = DateUtil.format (nowTime, DatePattern.NORM_DATE_PATTERN);
    
        // 获取当月每日一题生成情况
        String jsonStr = Optional.ofNullable (redisTemplate.opsForHash ().get (DAILY_QUESTION_KEY, yearMonth))
                .orElse ("").toString ();
        List<QuestionDailyDtoResult> array = StrUtil.isBlank (jsonStr) ?
                new ArrayList<> () :
                JSONUtil.parseArray (jsonStr).toList (QuestionDailyDtoResult.class);
    
        dailyQuestionId = array.stream ()
                .filter (o ->
                        DateUtil.betweenDay (o.getCreateTime (), nowTime, Boolean.TRUE) == NumConstant.NUM_0)
                .findFirst ()
                .orElse (new QuestionDailyDtoResult ()).getQuestionId ();
    }
}
