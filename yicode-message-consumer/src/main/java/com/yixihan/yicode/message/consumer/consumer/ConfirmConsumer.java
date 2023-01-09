package com.yixihan.yicode.message.consumer.consumer;

import com.rabbitmq.client.Channel;
import com.yixihan.yicode.message.consumer.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 发布确认-消费者
 *
 * @author yixihan
 * @date 2022-10-28-0:01
 */
@Slf4j
@Component
public class ConfirmConsumer {

    @RabbitListener(queues = ConfirmConfig.MESSAGE_QUEUE_NAME)
    public void receiveConfirmMessage (Message message, Channel channel) {
        try {
            log.info ("接受到的队列 confirm.queue 消息 : {}", new String (message.getBody ()));
            channel.basicAck (message.getMessageProperties ().getDeliveryTag (), false);
        } catch (Exception e) {
            log.info ("出现异常 : {}", e.getMessage ());
            throw new RuntimeException (e);
        }
    }
}
