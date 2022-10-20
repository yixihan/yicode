package com.yixihan.yicode.auth.component;

import com.yixihan.yicode.auth.pojo.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * jwt 增强器
 *
 * @author yixihan
 * @date 2022-10-20-16:30
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User securityUser = (User) authentication.getPrincipal ();

        // 自定义 jwt payload 信息
        Map<String, Object> info = new HashMap<> ();
        info.put ("userId", securityUser.getUserId ());
        info.put ("userName", securityUser.getUsername ());
        info.put ("userPhone", securityUser.getUserPhone ());
        info.put ("userEmail", securityUser.getUserEmail ());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation (info);

        return accessToken;
    }
}
