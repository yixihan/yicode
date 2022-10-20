package com.yixihan.yicode.auth.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 使用 jwt 存储 token 的配置
 *
 * @author yixihan
 * @date 2022-10-20-9:55
 */
@Configuration
public class JwtTokenStoreConfig {

    @Bean
    @Primary
    public TokenStore customerJwtTokenStore() {
        return new JwtTokenStore (jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        // 配置 jwt 使用的秘钥
        accessTokenConverter.setSigningKey("test_key");
        return accessTokenConverter;
    }
}
