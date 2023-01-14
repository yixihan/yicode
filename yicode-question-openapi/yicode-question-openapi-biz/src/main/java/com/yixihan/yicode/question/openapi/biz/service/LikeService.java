package com.yixihan.yicode.question.openapi.biz.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 点赞 服务类
 *
 * @author yixihan
 * @date 2023/1/14 13:02
 */
@Service
public class LikeService {
    
    @Resource
    private StringRedisTemplate redisTemplate;
    
    /**
     * 点赞 / 取消点赞
     */
    public Boolean setBit (String key, Long offset, Boolean value) {
        return redisTemplate.opsForValue ().setBit (key, offset, value);
    }
    
    /**
     * 判断是否已点赞
     *
     * @param key redis key
     * @param offset 点赞人 id
     * @return true : 已点赞 | false|null : 未点赞
     */
    public Boolean getBit (String key, Long offset) {
        return redisTemplate.opsForValue ().getBit (key, offset);
    }
}
