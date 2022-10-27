package com.yixihan.yicode.message.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 配置类 - 发布确认
 *
 * @author yixihan
 * @date 2022-10-27-23:30
 */
@Configuration
public class ConfirmConfig {


    /**
     * 交换机
     */
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";

    /**
     * 队列
     */
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";

    /**
     * route key
     */
    public static final String CONFIRM_ROUTING_KEY = "key1";

    /**
     * 备份交换机
     */
    public static final String BACKUP_EXCHANGE_NAME = "backup_exchange";

    /**
     * 备份队列
     */
    public static final String BACKUP_QUEUE_NAME = "backup_queue";

    /**
     * 报警队列
     */
    public static final String WARNING_QUEUE_NAME = "warning_queue";


    /**
     * 直连型交换机 - 确认发布
     *
     * @return
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange () {

        HashMap<String, Object> arguments = new HashMap<> (16);
        arguments.put ("alternate-exchange", BACKUP_EXCHANGE_NAME);

        return ExchangeBuilder.directExchange (CONFIRM_EXCHANGE_NAME)
                .durable (true)
                .withArguments (arguments)
                .build ();
    }

    /**
     * 备份交换机
     *
     * @return
     */
    @Bean("backupExchange")
    public FanoutExchange backupExchange () {
        return new FanoutExchange (BACKUP_EXCHANGE_NAME);
    }

    /**
     * 备份队列
     *
     * @return
     */
    @Bean("backupQueue")
    public Queue backupQueue () {
        return QueueBuilder.durable (BACKUP_QUEUE_NAME).build ();
    }

    /**
     * 报警队列
     * @return
     */
    @Bean("warningQueue")
    public Queue warningQueue () {
        return QueueBuilder.durable (WARNING_QUEUE_NAME).build ();
    }

    /**
     * 队列 - 发布确认
     * @return
     */
    @Bean("confirmQueue")
    public Queue confirmQueue () {
        return QueueBuilder.durable (CONFIRM_QUEUE_NAME).build ();
    }


    /**
     * 队列绑定交换机 - 发布确认队列
     *
     * @param confirmExchange
     * @param confirmQueue
     * @return
     */
    @Bean
    public Binding queueBindingExchange (
            @Qualifier("confirmExchange") DirectExchange confirmExchange,
            @Qualifier("confirmQueue") Queue confirmQueue
    ) {
        return BindingBuilder.bind (confirmQueue).to (confirmExchange).with (CONFIRM_ROUTING_KEY);
    }

    /**
     * 队列绑定交换机 - 备份队列
     *
     * @param confirmExchange
     * @param backupQueue
     * @return
     */
    @Bean
    public Binding queueBackupBindingExchangeBackup (
            @Qualifier("backupExchange") FanoutExchange confirmExchange,
            @Qualifier("backupQueue") Queue backupQueue
    ) {
        return BindingBuilder.bind (backupQueue).to (confirmExchange);
    }

    /**
     * 队列绑定交换机 - 报警队列
     *
     * @param confirmExchange
     * @param warningQueue
     * @return
     */
    @Bean
    public Binding queueWarningBindingExchangeBackup (
            @Qualifier("backupExchange") FanoutExchange confirmExchange,
            @Qualifier("warningQueue") Queue warningQueue
    ) {
        return BindingBuilder.bind (warningQueue).to (confirmExchange);
    }

}
