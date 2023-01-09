package com.yixihan.yicode.message.consumer.config;

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
     * 队列
     */
    public static final String MESSAGE_QUEUE_NAME = "yicode_message_queue";
    
}
