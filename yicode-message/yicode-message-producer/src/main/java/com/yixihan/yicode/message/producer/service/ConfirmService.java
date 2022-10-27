package com.yixihan.yicode.message.producer.service;

/**
 * 发布确认 - 生产者 - 服务类
 *
 * @author yixihan
 * @date 2022-10-27-23:55
 */
public interface ConfirmService {

    void sendMessage (String message);
}
