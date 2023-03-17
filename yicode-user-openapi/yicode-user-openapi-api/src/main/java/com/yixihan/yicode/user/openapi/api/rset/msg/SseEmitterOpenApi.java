package com.yixihan.yicode.user.openapi.api.rset.msg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * sse 通信 OpenApi
 *
 * @author yixihan
 * @date 2023/1/30 13:36
 */
@Api(tags = "sse 通信 OpenApi")
@RequestMapping("/open/user/sse")
public interface SseEmitterOpenApi {
    
    @ApiOperation ("建立 sse 连接")
    @PostMapping(value = "/connect", produces = "text/event-stream")
    SseEmitter connectSse();
    
    @ApiOperation ("关闭 sse 连接")
    @PostMapping(value = "/close", produces = "application/json")
    void closeSse();
    
}
