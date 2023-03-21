package com.yixihan.yicode.question.openapi.biz.service.question;

import com.yixihan.yicode.question.openapi.api.vo.response.question.CodeRunVO;
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
    SseEmitter connectSse();
    
    /**
     * 关闭 sse 连接
     */
    void closeSse();
    
    /**
     * 推送代码运行结果到客户端
     *
     * @param result 代码运行结果
     */
    void sendMsgToClient(CodeRunVO result);
}
