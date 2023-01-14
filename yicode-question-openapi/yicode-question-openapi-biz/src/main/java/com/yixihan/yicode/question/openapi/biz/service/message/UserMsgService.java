package com.yixihan.yicode.question.openapi.biz.service.message;

import com.yixihan.yicode.question.openapi.api.vo.request.AddMessageReq;


/**
 * 消息模块 服务类
 *
 * @author yixihan
 * @date 2023/1/10 16:51
 */
public interface UserMsgService {
    
    /**
     * 添加一条消息
     *
     * @param req 请求参数
     */
    void addMessage(AddMessageReq req);
}
