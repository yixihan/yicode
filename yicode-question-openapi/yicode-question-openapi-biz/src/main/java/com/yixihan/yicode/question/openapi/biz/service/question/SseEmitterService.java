package com.yixihan.yicode.question.openapi.biz.service.question;

import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


/**
 * sse 通信 服务类
 *
 * @author yixihan
 * @date 2023/1/30 13:41
 */
public interface SseEmitterService {
    
    /**
     * 建立 sse 连接, 以 userId 为 clientId
     *
     * @return {@link SseEmitter}
     */
    SseEmitter connectSse(Long userId);
    
    /**
     * 关闭 sse 连接
     */
    void closeSse(Long userId);
    
    /**
     * 推送消息到客户端
     */
    void sendMsgToClient(Long userId, CodeRunDtoResult result);
}