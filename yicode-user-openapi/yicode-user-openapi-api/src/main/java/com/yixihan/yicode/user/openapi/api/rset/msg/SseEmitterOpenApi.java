package com.yixihan.yicode.user.openapi.api.rset.msg;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping(value = "/connect", produces = "text/event-stream")
    SseEmitter connectSse(@RequestParam("userId") Long userId);
    
    @ApiOperation ("关闭 sse 连接")
    @GetMapping(value = "/close", produces = "application/json")
    void closeSse(@RequestParam("userId") Long userId);
    
}
