package com.yixihan.yicode.message.biz.service.impl;

import com.yixihan.yicode.common.constant.MessageConstant;
import com.yixihan.yicode.message.api.dto.request.MsgSendDtoReq;
import com.yixihan.yicode.message.biz.service.MessageService;
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
    public void sendMessage(MsgSendDtoReq dtoReq) {
        CorrelationData correlationData = new CorrelationData (dtoReq.getMessageId ());

        rabbitTemplate.convertAndSend (
                MessageConstant.MESSAGE_EXCHANGE_NAME,
                MessageConstant.MESSAGE_ROUTING_KEY,
                dtoReq.getData (),
                correlationData
        );
    }
    
    @Override
    public void commit(MsgSendDtoReq dtoReq) {
        CorrelationData correlationData = new CorrelationData (dtoReq.getMessageId ());
    
        rabbitTemplate.convertAndSend (
                MessageConstant.TASK_COMMIT_EXCHANGE_NAME,
                MessageConstant.TASK_COMMIT_ROUTING_KEY,
                dtoReq.getData (),
                correlationData
        );
    }
    
    @Override
    public void test(MsgSendDtoReq dtoReq) {
        CorrelationData correlationData = new CorrelationData (dtoReq.getMessageId ());
    
        rabbitTemplate.convertAndSend (
                MessageConstant.TASK_TEST_EXCHANGE_NAME,
                MessageConstant.TASK_TEST_ROUTING_KEY,
                dtoReq.getData (),
                correlationData
        );
    }
}
