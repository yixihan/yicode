package com.yixihan.yicode.message.api.constant;

/**
 * 消息模块 常量类
 *
 * @author yixihan
 * @date 2023/1/10 9:25
 */
public class MessageConstant {
    
    
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
}
