package com.yixihan.yicode.auth.server.component;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt 内容增强器
 *
 * @author yixihan
 * @date 2022-10-20-10:03
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<> (16);
        info.put ("enhance", "enhance info");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation (info);
        return accessToken;
    }
}
