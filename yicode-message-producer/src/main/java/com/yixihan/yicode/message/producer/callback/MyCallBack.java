package com.yixihan.yicode.message.producer.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 自定义回调接口
 *
 * @author yixihan
 * @date 2022-10-28-0:02
 */
@Component
@Slf4j
public class MyCallBack implements RabbitTemplate.ConfirmCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init () {
        rabbitTemplate.setConfirmCallback (this);
    }

    /**
     * void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause);
     * 交换机确认回调方法
     * 1. 发消息 交换机收到了 回调
     * correlationData : 保存回调消息的 ID 及相关信息
     * ack : 交换机收到消息 ack = true
     * cause : null
     * 2. 发消息, 交换机接收失败了 回调
     * correlationData : 保存回调消息的 ID 及相关信息
     * ack : 交换机收到消息 ack = false
     * cause : 失败的原因
     *
     * @param correlationData 保存回调消息的 ID 及相关信息
     * @param ack             交换机收到信息
     * @param cause           失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId () : "";
        if (ack) {
            log.info ("交换机已经收到了 ID 为 : {} 的消息", id);
        } else {
            log.error ("交换机还未收到 ID 为 : {} 的消息, 失败的原因是 : {}", id, cause);
        }
    }
}
