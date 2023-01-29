package com.yixihan.yicode.message.biz.config;

import com.yixihan.yicode.message.api.constant.MessageConstant;
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
     * 消息系统 - 直连型交换机
     */
    @Bean("messageExchange")
    public DirectExchange messageExchange() {
        return new DirectExchange (MessageConstant.MESSAGE_EXCHANGE_NAME);
    }
    
    /**
     * 消息系统 - 队列
     */
    @Bean("messageQueue")
    public Queue messageQueue() {
        return QueueBuilder.durable (MessageConstant.MESSAGE_QUEUE_NAME).build ();
    }
    
    /**
     * 消息系统 - 队列绑定交换机-发布确认队列
     */
    @Bean
    public Binding messageQueueBindingExchange(@Qualifier("messageExchange") DirectExchange messageExchange,
                                               @Qualifier("messageQueue") Queue messageQueue) {
        return BindingBuilder.bind (messageQueue).to (messageExchange).with (MessageConstant.MESSAGE_ROUTING_KEY);
    }
    
    /**
     * 测评系统 - 提交代码 - 直连型交换机
     */
    @Bean("taskCommitExchange")
    public DirectExchange taskCommitExchange() {
        return new DirectExchange (MessageConstant.TASK_COMMIT_EXCHANGE_NAME);
    }
    
    /**
     * 测评系统 - 提交代码 - 队列
     */
    @Bean("taskCommitQueue")
    public Queue taskCommitQueue() {
        return QueueBuilder.durable (MessageConstant.TASK_COMMIT_QUEUE_NAME).build ();
    }
    
    /**
     * 测评系统 - 提交代码 - 队列绑定交换机-发布确认队列
     */
    @Bean
    public Binding taskCommitQueueBindingExchange(@Qualifier("taskCommitExchange") DirectExchange taskExchange,
                                                  @Qualifier("taskCommitQueue") Queue taskQueue) {
        return BindingBuilder.bind (taskQueue).to (taskExchange).with (MessageConstant.TASK_COMMIT_ROUTING_KEY);
    }
    /**
     * 测评系统 - 提交代码 - 直连型交换机
     */
    @Bean("taskTestExchange")
    public DirectExchange taskTestExchange() {
        return new DirectExchange (MessageConstant.TASK_COMMIT_EXCHANGE_NAME);
    }
    
    /**
     * 测评系统 - 提交代码 - 队列
     */
    @Bean("taskTestQueue")
    public Queue taskTestQueue() {
        return QueueBuilder.durable (MessageConstant.TASK_COMMIT_QUEUE_NAME).build ();
    }
    
    /**
     * 测评系统 - 提交代码 - 队列绑定交换机-发布确认队列
     */
    @Bean
    public Binding taskTestQueueBindingExchange(@Qualifier("taskTestExchange") DirectExchange taskExchange,
                                                  @Qualifier("taskTestQueue") Queue taskQueue) {
        return BindingBuilder.bind (taskQueue).to (taskExchange).with (MessageConstant.TASK_COMMIT_ROUTING_KEY);
    }
}
