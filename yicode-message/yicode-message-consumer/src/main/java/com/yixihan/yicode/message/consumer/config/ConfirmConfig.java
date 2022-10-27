package com.yixihan.yicode.message.consumer.config;

import org.springframework.context.annotation.Configuration;

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



}
