package com.yixihan.yicode.question.openapi.biz.service;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.yixihan.yicode.message.api.constant.MessageConstant;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;
import com.yixihan.yicode.question.openapi.biz.feign.run.CodeRunFeignClient;
import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    
    @RabbitListener(queues = MessageConstant.TASK_QUEUE_NAME)
    public void commit (Message message, Channel channel) {
        try {
            String arg = new String (message.getBody ());
            log.info ("接受到的队列 confirm.queue 消息 : {}", arg);
    
            CodeReq req = JSONUtil.toBean (arg, CodeReq.class);
    
            CodeRunDtoReq dtoReq = new CodeRunDtoReq ();
            dtoReq.setCode (req.getCode ());
            dtoReq.setCodeType (req.getType ());
            
            CodeRunDtoResult result = codeRunFeignClient.runCode (dtoReq).getResult ();
    
            log.info ("代码运行结果 : {}", result );
    
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (Exception e) {
            log.info ("出现异常 : {}", e.getMessage ());
            throw new RuntimeException (e);
        }
    }
    
}
