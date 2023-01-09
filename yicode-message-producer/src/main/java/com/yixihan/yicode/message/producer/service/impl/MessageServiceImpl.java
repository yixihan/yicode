package com.yixihan.yicode.message.producer.service.impl;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.message.producer.config.ConfirmConfig;
import com.yixihan.yicode.message.producer.dto.request.MsgSendDtoReq;
import com.yixihan.yicode.message.producer.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发布确认-生产者-服务类
 *
 * @author yixihan
 * @date 2022-10-27-23:56
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;


    @Override
    public CommonDtoResult<Boolean> sendMessage(MsgSendDtoReq dtoReq) {
        CorrelationData correlationData = new CorrelationData (dtoReq.getMessageId ());

        rabbitTemplate.convertAndSend (
                ConfirmConfig.MESSAGE_EXCHANGE_NAME,
                ConfirmConfig.MESSAGE_ROUTING_KEY,
                dtoReq.getData (),
                correlationData
        );
        
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> sendTask(MsgSendDtoReq dtoReq) {
        CorrelationData correlationData = new CorrelationData (dtoReq.getMessageId ());
    
        rabbitTemplate.convertAndSend (
                ConfirmConfig.TASK_EXCHANGE_NAME,
                ConfirmConfig.TASK_ROUTING_KEY,
                dtoReq.getData (),
                correlationData
        );
    
        return new CommonDtoResult<> (Boolean.TRUE);
    }
}
