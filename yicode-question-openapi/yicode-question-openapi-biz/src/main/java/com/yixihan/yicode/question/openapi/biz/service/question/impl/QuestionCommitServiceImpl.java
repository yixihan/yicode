package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.message.api.dto.request.MsgSendDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.CodeCommitCountDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.UserQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.CodeRateDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.CommitRecordDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionAnswerDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.UserQuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CodeRateVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CommitRecordVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionAnswerVO;
import com.yixihan.yicode.question.openapi.biz.feign.message.TaskFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionAnswerFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionCommitService;
import com.yixihan.yicode.question.openapi.biz.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.yixihan.yicode.common.constant.RedisKeyConstant.COMMIT_RUN_CODE_KEY;
import static com.yixihan.yicode.common.constant.RedisKeyConstant.TEST_RUN_CODE_KEY;

/**
 * 提交答案 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 12:22
 */
@Slf4j
@Service
public class QuestionCommitServiceImpl implements QuestionCommitService {
    
    @Resource
    private UserService userService;
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    @Resource
    private QuestionAnswerFeignClient questionAnswerFeignClient;
    
    @Resource
    private TaskFeignClient taskFeignClient;
    
    @Override
    public void test(CodeReq req) {
        // 参数校验
        Assert.notNull (req.getQuestionId ());
        Assert.isFalse (StrUtil.isBlank (req.getCode ()));
        Assert.isFalse (StrUtil.isBlank (req.getParam ()));
        
        // redis 限流
        Long userId = userService.getUserId ();
        String key = String.format (TEST_RUN_CODE_KEY, userId);
        Assert.isFalse (redisTemplate.hasKey (key), BizCodeEnum.CODE_FLOW_ERR);
        redisTemplate.opsForValue ().set (key, key, 15, TimeUnit.SECONDS);
        
        // 发送到任务队列
        MsgSendDtoReq dtoReq = new MsgSendDtoReq ();
        dtoReq.setData (JSONUtil.toJsonStr (req));
        dtoReq.setMessageId (String.valueOf (userId + System.currentTimeMillis ()));
        taskFeignClient.test (dtoReq);
    }
    
    @Override
    public void commit(CodeReq req) {
        // 参数校验
        Assert.notNull (req.getQuestionId ());
        Assert.isFalse (StrUtil.isBlank (req.getCode ()));
    
    
        // redis 限流
        Long userId = userService.getUserId ();
        String key = String.format (COMMIT_RUN_CODE_KEY, userId);
        Assert.isFalse (redisTemplate.hasKey (key), BizCodeEnum.CODE_FLOW_ERR);
        redisTemplate.opsForValue ().set (key, key, 15, TimeUnit.SECONDS);
    
        // 发送到任务队列
        MsgSendDtoReq dtoReq = new MsgSendDtoReq ();
        dtoReq.setData (JSONUtil.toJsonStr (req));
        dtoReq.setMessageId (String.valueOf (userId + System.currentTimeMillis ()));
        taskFeignClient.commit (dtoReq);
    }
    
    @Override
    public PageVO<QuestionAnswerVO> queryQuestionAnswer(QuestionAnswerReq req) {
        // 构造请求 body
        QuestionAnswerDtoReq dtoReq = BeanUtil.toBean (req, QuestionAnswerDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
    
        // 获取单个问题提交记录
        PageDtoResult<QuestionAnswerDtoResult> dtoResult = questionAnswerFeignClient.queryQuestionAnswer (dtoReq).getResult ();
        
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, QuestionAnswerVO.class)
        );
    }
    
    @Override
    public PageVO<QuestionAnswerVO> queryUserQuestionAnswer(UserQuestionAnswerReq req) {
        // 构造请求 body
        UserQuestionAnswerDtoReq dtoReq = BeanUtil.toBean (req, UserQuestionAnswerDtoReq.class);
        dtoReq.setUserId (userService.getUserId ());
    
        // 获取用户问题提交记录
        PageDtoResult<QuestionAnswerDtoResult> dtoResult = questionAnswerFeignClient.queryUserQuestionAnswer (dtoReq)
                .getResult ();
    
    
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, QuestionAnswerVO.class)
        );
    }
    
    @Override
    public List<CommitRecordVO> codeCommitCount(String month) {
        // 设置 endDate & startDate
        Date endDate = StrUtil.isBlank (month) ?
                DateUtil.endOfDay (new Date ()) :
                DateUtil.endOfMonth (DateUtil.parse (month, "yyyy-MM"));
        
        Date startDate = DateUtil.beginOfMonth (endDate);
        
    
        // 构建请求 body
        CodeCommitCountDtoReq dtoReq = new CodeCommitCountDtoReq ();
        dtoReq.setUserId (userService.getUser ().getUserId ());
        dtoReq.setEndDate (DateUtil.format (endDate, DatePattern.NORM_DATETIME_PATTERN));
        dtoReq.setStartDate (DateUtil.format (startDate, DatePattern.NORM_DATETIME_PATTERN));
        
        // 获取用户提交代码次数记录
        List<CommitRecordDtoResult> dtoResult = questionAnswerFeignClient.codeCommitCount (dtoReq).getResult ();
        
        return BeanUtil.copyToList (dtoResult, CommitRecordVO.class);
    }
    
    @Override
    public CodeRateVO codeRate() {
        Long userId = userService.getUserId ();
    
        CodeRateDtoResult dtoResult = questionAnswerFeignClient.codeRate (userId).getResult ();
        
        return BeanUtil.toBean (dtoResult, CodeRateVO.class);
    }
}
