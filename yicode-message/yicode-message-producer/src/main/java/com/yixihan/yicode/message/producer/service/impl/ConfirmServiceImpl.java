package com.yixihan.yicode.message.producer.service.impl;

import com.yixihan.yicode.message.producer.config.ConfirmConfig;
import com.yixihan.yicode.message.producer.service.ConfirmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发布确认 - 生产者 - 服务类
 *
 * @author yixihan
 * @date 2022-10-27-23:56
 */
@Slf4j
@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Resource
    private RabbitTemplate rabbitTemplate;


    @Override
    public void sendMessage(String message) {
        CorrelationData correlationData = new CorrelationData ("1");

        rabbitTemplate.convertAndSend (
                ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                ConfirmConfig.CONFIRM_ROUTING_KEY,
                message,
                correlationData
        );

        log.info ("发送消息内容 : {}", message);
    }
}
