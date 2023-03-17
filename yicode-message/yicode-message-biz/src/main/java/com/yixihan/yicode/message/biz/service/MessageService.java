package com.yixihan.yicode.message.biz.service;

import com.yixihan.yicode.message.api.dto.request.MsgSendDtoReq;


/**
 * 发布确认-生产者-服务类
 *
 * @author yixihan
 * @date 2022-10-27-23:55
 */
public interface MessageService {
    
    /**
     * 发送消息
     */
    void sendMessage(MsgSendDtoReq dtoReq);
    
    /**
     * 发送任务-提交代码
     */
    void commit(MsgSendDtoReq dtoReq);
    
    /**
     * 发送任务-自测代码
     */
    void test(MsgSendDtoReq dtoReq);
}
