package com.yixihan.yicode.question.openapi.biz.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 点赞 服务类
 *
 * @author yixihan
 * @date 2023/1/14 13:02
 */
@Service
public class LikeService {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 点赞
     *
     * @param key redis key
     * @param sourceKey 具体内容的 redis key
     * @param likeUserId 点赞用户
     * @return 点赞数
     */
    public Integer like (String key, Long sourceKey, Long likeUserId) {
        // 获取点赞列表
        Set<Long> likeSet = JSONUtil.parseArray (redisTemplate.opsForHash ().get (key, sourceKey.toString ()))
                .stream ().map (Object::toString).map (Long::parseLong)
                .collect(Collectors.toSet());
        // 添加点赞用户
        likeSet.add (likeUserId);
    
        // 存储进 redis
        JSONArray jsonArray = JSONUtil.createArray ();
        jsonArray.addAll (likeSet);
        redisTemplate.opsForHash ().put (key, sourceKey.toString (), jsonArray);
        return likeSet.size ();
    }
    
    /**
     * 取消点赞
     *
     * @param key redis key
     * @param sourceKey 具体内容的 redis key
     * @param unLikeUserId 取消点赞用户
     * @return 点赞数
     */
    public Integer unLike (String key, Long sourceKey, Long unLikeUserId) {
        // 获取点赞列表
        Set<Long> likeSet = JSONUtil.parseArray (redisTemplate.opsForHash ().get (key, sourceKey.toString ()))
                .stream ().map (Object::toString).map (Long::parseLong)
                .collect(Collectors.toSet());
        // 添加点赞用户
        likeSet.remove (unLikeUserId);
        
        // 存储进 redis
        JSONArray jsonArray = JSONUtil.createArray ();
        jsonArray.addAll (likeSet);
        redisTemplate.opsForHash ().put (key, sourceKey.toString (), jsonArray);
        return likeSet.size ();
    }
    
    /**
     * 判断是否已点赞
     *
     * @param key redis key
     * @param sourceKey 具体内容的 redis key
     * @param userId 取消点赞用户
     * @return true : 已点赞 | false : 未点赞
     */
    public Boolean isLike (String key, Long sourceKey, Long userId) {
        Set<Long> likeSet = JSONUtil.parseArray (redisTemplate.opsForHash ().get (key, sourceKey.toString ()))
                .stream ().map (Object::toString).map (Long::parseLong)
                .collect(Collectors.toSet());
        
        return likeSet.contains (userId);
    }
    
    /**
     * 获取点赞数量
     *
     * @param key redis key
     * @param sourceKey 具体内容的 redis key
     * @return 点赞数
     */
    public Integer getLikeCount (String key, Long sourceKey) {
        return JSONUtil.parseArray (redisTemplate.opsForHash ().get (key, sourceKey.toString ())).size ();
    }
}
