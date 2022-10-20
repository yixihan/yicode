package com.yixihan.yicode.auth.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * 使用 redis 存储 token 的配置
 *
 * @author yixihan
 * @date 2022-10-20-9:53
 */
@Configuration
public class RedisTokenStoreConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore redisTokenStore (){
        return new RedisTokenStore (redisConnectionFactory);
    }
}
