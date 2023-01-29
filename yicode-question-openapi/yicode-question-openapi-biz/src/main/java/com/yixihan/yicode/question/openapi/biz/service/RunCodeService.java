package com.yixihan.yicode.question.openapi.biz.service;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.yixihan.yicode.message.api.constant.MessageConstant;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCaseDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionCaseFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.run.CodeRunFeignClient;
import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    
    @RabbitListener(queues = MessageConstant.TASK_QUEUE_NAME)
    public void commit (Message message, Channel channel) {
        try {
    
            // 获取 req
            CodeReq req = JSONUtil.toBean (new String (message.getBody ()), CodeReq.class);
            log.info ("req : {}", req);
            
            // 获取测试用例
            List<QuestionCaseDtoResult> questionCase = questionCaseFeignClient.allQuestionCase (req.getQuestionId ())
                    .getResult ();
            
            // 构建请求 body
            CodeRunDtoReq dtoReq = new CodeRunDtoReq ();
            dtoReq.setCode (req.getCode ());
            dtoReq.setCodeType (req.getType ());
            dtoReq.setParamList (questionCase.stream ().map (QuestionCaseDtoResult::getCaseParams).collect(Collectors.toList()));
            
            CodeRunDtoResult result = codeRunFeignClient.runCode (dtoReq).getResult ();
    
            log.info ("代码运行结果 : {}", result );
    
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (Exception e) {
            log.info ("出现异常 : {}", e.getMessage ());
            throw new RuntimeException (e);
        }
    }
    
}
