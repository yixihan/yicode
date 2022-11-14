package com.yixihan.yicode.auth.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * oauth2 参数类
 *
 * @author yixihan
 * @date 2022-10-21-11:35
 */
@Configuration
@Getter
public class Oauth2ServerProperties {


    @Value("${yicode.auth.client.client-id}")
    private String clientId;

    @Value("${yicode.auth.client.raw-password}")
    private String rawPassword;

    @Value("${yicode.auth.client.scopes}")
    private String scopes;

    @Value("${yicode.auth.client.access-token-validity-seconds}")
    private Integer accessTokenValiditySeconds;

    @Value("${yicode.auth.client.refresh-token-validity-seconds}")
    private Integer refreshTokenValiditySeconds;

    @Value("${yicode.auth.jwt.file-name}")
    private String fileName;

    @Value("${yicode.auth.jwt.password}")
    private String password;
}
