package com.yixihan.yicode.user.openapi.biz.service.msg;

import com.yixihan.yicode.user.openapi.api.vo.response.msg.MessageDetailVO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

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
    SseEmitter connectSse(@RequestParam("userId") Long userId);
    
    /**
     * 关闭 sse 连接
     */
    void closeSse(@RequestParam("userId") Long userId);
    
    /**
     * 推送消息到客户端
     *
     * @param messageList 消息内容
     */
    void sendMsgToClient(List<MessageDetailVO> messageList);
}
