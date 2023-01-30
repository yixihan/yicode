package com.yixihan.yicode.user.openapi.web.controller.msg;

import com.yixihan.yicode.user.openapi.api.rset.msg.SseEmitterOpenApi;
import com.yixihan.yicode.user.openapi.biz.service.msg.SseEmitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;

/**
 * sse 通信 前端控制器
 *
 * @author yixihan
 * @date 2023/1/30 13:40
 */
@Slf4j
@RestController
public class SseEmitterController implements SseEmitterOpenApi {
    
    @Resource
    private SseEmitterService service;
    
    @Override
    public SseEmitter connectSse(Long userId) {
        return service.connectSse (userId);
    }
    
    @CrossOrigin
    @Override
    public void closeSse(Long userId) {
        service.closeSse (userId);
    }
}
