package com.yixihan.yicode.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yixihan
 * @date 2022-10-21-11:18
 */
@SpringBootTest
public class YamlTest {

    @Value("${yicode.auth.client.client-id}")
    private String clientId;

    @Value("${yicode.auth.client.raw-password}")
    private String rawPassword;

    @Value("${yicode.auth.client.scopes}")
    private String scopes;

    @Value("${yicode.auth.client.grand-type}")
    private String grandType;

    @Value("${yicode.auth.client.access-token-validity-seconds}")
    private Integer accessTokenValiditySeconds;

    @Value("${yicode.auth.client.refresh-token-validity-seconds}")
    private Integer refreshTokenValiditySeconds;

    @Value("${yicode.auth.jwt.file-name}")
    private String fileName;

    @Value("${yicode.auth.jwt.password}")
    private String password;

    @Test
    public void testGetProperty () {
        assert "yicode".equals (clientId);
        assert "yicode".equals (rawPassword);
        assert "all".equals (scopes);
        System.out.println (grandType);
        assert accessTokenValiditySeconds == 3600;
        assert refreshTokenValiditySeconds == 86400;
        assert "jwt.jks".equals (fileName);
        assert "yicode".equals (password);
    }
}
