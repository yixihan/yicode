package com.yixihan.yicode.message.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类-发布确认
 *
 * @author yixihan
 * @date 2022-10-27-23:30
 */
@Configuration
public class ConfirmConfig {
    
    
    /**
     * 消息系统 - 交换机
     */
    public static final String MESSAGE_EXCHANGE_NAME = "yicode_message_exchange";
    
    /**
     * 消息系统 - 队列
     */
    public static final String MESSAGE_QUEUE_NAME = "yicode_message_queue";
    
    /**
     * 消息系统 - route key
     */
    public static final String MESSAGE_ROUTING_KEY = "yicode_message_key";
    
    /**
     * 测评系统 - 交换机
     */
    public static final String TASK_EXCHANGE_NAME = "yicode_task_exchange";
    
    /**
     * 测评系统 - 队列
     */
    public static final String TASK_QUEUE_NAME = "yicode_task_queue";
    
    /**
     * 测评系统 - route key
     */
    public static final String TASK_ROUTING_KEY = "yicode_task_key";
    
    /**
     * 消息系统 - 直连型交换机
     */
    @Bean("messageExchange")
    public DirectExchange messageExchange() {
        return new DirectExchange (MESSAGE_EXCHANGE_NAME);
    }
    
    /**
     * 消息系统 - 队列
     */
    @Bean("messageQueue")
    public Queue messageQueue() {
        return QueueBuilder.durable (MESSAGE_QUEUE_NAME).build ();
    }
    
    /**
     * 消息系统 - 队列绑定交换机-发布确认队列
     */
    @Bean
    public Binding messageQueueBindingExchange(@Qualifier("messageExchange") DirectExchange messageExchange, @Qualifier("messageQueue") Queue messageQueue) {
        return BindingBuilder.bind (messageQueue).to (messageExchange).with (MESSAGE_ROUTING_KEY);
    }
    
    /**
     * 测评系统 - 直连型交换机
     */
    @Bean("taskExchange")
    public DirectExchange taskExchange() {
        return new DirectExchange (TASK_EXCHANGE_NAME);
    }
    
    /**
     * 测评系统 - 队列
     */
    @Bean("taskQueue")
    public Queue taskQueue() {
        return QueueBuilder.durable (TASK_QUEUE_NAME).build ();
    }
    
    /**
     * 测评系统 - 队列绑定交换机-发布确认队列
     */
    @Bean
    public Binding taskQueueBindingExchange(@Qualifier("taskExchange") DirectExchange taskExchange, @Qualifier("taskQueue") Queue taskQueue) {
        return BindingBuilder.bind (taskQueue).to (taskExchange).with (TASK_ROUTING_KEY);
    }
}
