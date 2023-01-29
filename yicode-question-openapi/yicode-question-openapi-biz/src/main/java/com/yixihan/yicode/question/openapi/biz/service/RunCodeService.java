package com.yixihan.yicode.question.openapi.biz.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.yixihan.yicode.message.api.constant.MessageConstant;
import com.yixihan.yicode.question.api.dto.request.question.AddQuestionAnswerDtoReq;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCaseDtoResult;
import com.yixihan.yicode.question.openapi.api.enums.CodeAnswerEnums;
import com.yixihan.yicode.question.openapi.api.prop.CodeJudgeProp;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionAnswerFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionCaseFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.run.CodeRunFeignClient;
import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

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
    private CodeRunFeignClient codeRunFeignClient;
    
    @Resource
    private QuestionCaseFeignClient questionCaseFeignClient;
    
    @Resource
    private QuestionAnswerFeignClient questionAnswerFeignClient;
    
    @Resource
    private CodeJudgeProp codeJudgeProp;
    
    @RabbitListener(queues = MessageConstant.TASK_TEST_QUEUE_NAME)
    public void test(Message message, Channel channel) {
        try {
            // 获取 req
            CodeReq req = JSONUtil.toBean (new String (message.getBody ()), CodeReq.class);
            
            // 获取测试用例
            List<QuestionCaseDtoResult> questionCase = questionCaseFeignClient.allQuestionCase (req.getQuestionId ()).getResult ();
            
            // 构建请求 body
            CodeRunDtoReq dtoReq = new CodeRunDtoReq ();
            dtoReq.setCode (req.getCode ());
            dtoReq.setCodeType (req.getType ());
            dtoReq.setParamList (questionCase.stream ()
                    .map (QuestionCaseDtoResult::getCaseParams).collect (Collectors.toList ()));
            
            // 运行代码
            CodeRunDtoResult result = codeRunFeignClient.runCode (dtoReq).getResult ();
            log.info ("代码运行结果 : {}", result);
            
            // TODO 推送给前端
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (Exception e) {
            log.error ("出现异常", e);
            throw new RuntimeException (e);
        }
    }
    
    @RabbitListener(queues = MessageConstant.TASK_COMMIT_QUEUE_NAME)
    public void commit(Message message, Channel channel) {
        CodeAnswerEnums status = null;
        CodeRunDtoResult result = null;
        // 获取 req
        CodeReq req = JSONUtil.toBean (new String (message.getBody ()), CodeReq.class);
        try {
            // 获取测试用例
            List<QuestionCaseDtoResult> questionCase = questionCaseFeignClient.allQuestionCase (req.getQuestionId ()).getResult ();
    
            // 构建请求 body
            CodeRunDtoReq dtoReq = new CodeRunDtoReq ();
            dtoReq.setCode (req.getCode ());
            dtoReq.setCodeType (req.getType ());
            dtoReq.setParamList (questionCase.stream ()
                    .map (QuestionCaseDtoResult::getCaseParams).collect (Collectors.toList ()));
            
            
            // 运行代码
            result = codeRunFeignClient.runCode (dtoReq).getResult ();
            
            // TODO 推送给前端
            // 编译失败
            if (!result.getCompile ()) {
                status = CodeAnswerEnums.CE;
                
                // 推送消息
                log.info ("代码运行结果 : {}", status.getAnswer ());
                // 保存到数据库
                saveQuestionAnswer (req, result, status);
                channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
                return;
            }
            
            // 运行失败
            if (!result.getRun ()) {
                status = CodeAnswerEnums.RE;
                
                // 推送消息
                log.info ("代码运行结果 : {}", status.getAnswer ());
                // 保存到数据库
                saveQuestionAnswer (req, result, status);
                channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
                return;
            }
            
            // 编译, 运行成功
            List<String> paramAnsList = questionCase.stream ()
                    .map (QuestionCaseDtoResult::getCaseAnswer).collect (Collectors.toList ());
            List<String> runAnsList = result.getAnsList ();
            
            // 长度不一
            if (paramAnsList.size () != runAnsList.size ()) {
                status = CodeAnswerEnums.WA;
                
                // 推送消息
                log.info ("代码运行结果 : {}", status.getAnswer ());
                // 保存到数据库
                saveQuestionAnswer (req, result, status);
                channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
                return;
            }
            
            // 逐个对比
            boolean flag = true;
            int len = 0;
            for (int i = 0; i < paramAnsList.size (); i++) {
                len += runAnsList.get (i).length ();
                if (len >= codeJudgeProp.getMaxOutputLen ()) {
                    status = CodeAnswerEnums.OL;
                    flag = false;
                    break;
                }
                if (StrUtil.equals (paramAnsList.get (i), runAnsList.get (i))) {
                    continue;
                }
                String replaceStr = runAnsList.get (i).replaceAll ("\\s*", "");
                if (StrUtil.equals (paramAnsList.get (i), replaceStr)) {
                    flag = false;
                    status = CodeAnswerEnums.PE;
                    break;
                } else {
                    flag = false;
                    break;
                }
            }
            
            status = status == null ? flag ? CodeAnswerEnums.AC : CodeAnswerEnums.WA : status;
            
            // 推送消息
            log.info ("代码运行结果 : {}", status.getAnswer ());
            // 保存到数据库
            saveQuestionAnswer (req, result, status);
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (RetryableException e) {
            // 超时
            status = CodeAnswerEnums.TLE;
            
            // 推送消息
            log.info ("代码运行结果 : {}", status.getAnswer ());
            // 保存到数据库
            saveQuestionAnswer (req, result, status);
        } catch (Exception e) {
            // 系统内部错误
            status = CodeAnswerEnums.SE;
            
            // 推送消息
            log.info ("代码运行结果 : {}", status.getAnswer ());
            // 保存到数据库
            saveQuestionAnswer (req, result, status);
        }
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
    
    
}
