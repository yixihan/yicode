package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.yixihan.yicode.common.constant.SseEmitterConstant;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.question.openapi.biz.service.question.SseEmitterService;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * sse 通信 服务实现类
 *
 * @author yixihan
 * @date 2023/1/30 13:41
 */
@Slf4j
@Service
public class SseEmitterServiceImpl implements SseEmitterService {
    
    /**
     * 容器, 保存连接, 用于输出返回
     */
    private static final Map<Long, SseEmitter> sseCache = new ConcurrentHashMap<> ();
    
    @Override
    public SseEmitter connectSse(Long userId) {
        // 设置超时时间, 0 表示不过期, 默认 30 秒, 超过时间未完成会抛出异常 : AsyncRequestTimeoutException
        SseEmitter sseEmitter = new SseEmitter(0L);
    
        // 注册回调
        sseEmitter.onCompletion(completionCallBack(userId));
        
        sseCache.put(userId, sseEmitter);
        log.info ("sseCache : {}",  sseCache);
        log.info("创建新的sse连接，当前用户：{}", userId);
    
        try {
            sseEmitter.send(SseEmitter.event().data(userId));
        } catch (IOException e) {
            log.error("SseEmitterServiceImpl[createSseConnect] : 创建长链接异常, 用户 ID : {}", userId, e);
            throw new BizException ("创建连接异常！");
        }
        return sseEmitter;
    }
    
    @Override
    public void closeSse(Long userId) {
        SseEmitter sseEmitter = sseCache.get(userId);
        if (sseEmitter != null) {
            sseEmitter.complete();
            removeUser(userId);
        }
    }
    
    @Override
    public void sendMsgToClient(Long userId, CodeRunDtoResult result) {
        if (CollectionUtil.isEmpty(sseCache)) {
            return;
        }
        
        if (sseCache.containsKey (userId)) {
            sendMsgToClientByClientId(userId, result);
        }

    }
    
    /**
     * 推送消息到客户端
     *
     * @param userId 用户 ID
     * @param message 信息明细
     */
    private void sendMsgToClientByClientId(Long userId, CodeRunDtoResult message) {
        SseEmitter sseEmitter = sseCache.get (userId);
    
        SseEmitter.SseEventBuilder sendData = SseEmitter.event()
                .id(SseEmitterConstant.TASK_RESULT)
                .data(message, MediaType.APPLICATION_JSON);
        try {
            sseEmitter.send(sendData);
        } catch (IOException e) {
            // 推送消息失败
            log.error("SseEmitterServiceImpl[sendMsgToClient] : 推送消息失败,尝试进行重推", e);
        }
    }
    
    /**
     * 长链接完成后回调接口(即关闭连接时调用)
     *
     * @param userId 用户 ID
     * @return java.lang.Runnable
     */
    private Runnable completionCallBack(Long userId) {
        return () -> {
            log.info("结束连接：{}", userId);
            removeUser(userId);
        };
    }
    
    /**
     * 移除用户连接
     *
     * @param userId 用户 ID
     * @author re
     * @date 2021/12/14
     */
    private void removeUser(Long userId) {
        sseCache.remove(userId);
        log.info("SseEmitterServiceImpl[removeUser] : 移除用户 : {}", userId);
    }
    
    
}