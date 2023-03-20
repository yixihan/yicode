package com.yixihan.yicode.auth.oauth.password;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.yixihan.yicode.auth.service.UserService;
import com.yixihan.yicode.auth.service.impl.UserServiceImpl;
import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义用户名密码验证码严重类
 *
 * @author yixihan
 * @date 2022/11/21 16:40
 */
public class PasswordTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "verify_code";

    private final AuthenticationManager authenticationManager;

    public PasswordTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        this (authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
    }

    protected PasswordTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super (tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<> (tokenRequest.getRequestParameters ());
        // 校验验证码
        checkPhotoCode (parameters);
        String username = parameters.get (AuthConstant.USERNAME);
        String password = parameters.get (AuthConstant.PASSWORD);
        // Protect from downstream leaks of password
        parameters.remove ("password");

        Authentication userAuth = new UsernamePasswordAuthenticationToken (username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails (parameters);
        try {
            userAuth = authenticationManager.authenticate (userAuth);
        } catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException (ase.getMessage ());
        } catch (BadCredentialsException e) {
            // If the username/password are wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException (e.getMessage ());
        }
        if (userAuth == null || !userAuth.isAuthenticated ()) {
            throw new InvalidGrantException ("Could not authenticate user: " + username);
        }

        OAuth2Request oAuth2Request = getRequestFactory ().createOAuth2Request (client, tokenRequest);
        return new OAuth2Authentication (oAuth2Request, userAuth);
    }

    /**
     * 校验 图片 验证码
     */
    private void checkPhotoCode(Map<String, String> parameters) {
        String uuid = parameters.get ("uuid");
        String code = parameters.get ("code");

        if (StrUtil.isBlank (uuid)) {
            throw new OAuth2Exception (BizCodeEnum.UUID_VALIDATE_ERR.getErrorMsg ());
        }
        if (StrUtil.isBlank (code)) {
            throw new OAuth2Exception (BizCodeEnum.CODE_EMPTY_ERR.getErrorMsg ());
        }

        UserService userService = getUserService ();
        CodeValidateDtoReq dtoReq = new CodeValidateDtoReq ();
        dtoReq.setUuid (uuid);
        dtoReq.setCode (code);
        try {
            userService.validatePhotoCode (dtoReq);
        } catch (Exception e) {
            throw new OAuth2Exception (BizCodeEnum.CODE_VALIDATE_ERR.getErrorMsg ());
        }
    }

    /**
     * 获取 userService
     *
     * @return {@link UserService}
     */
    private UserService getUserService() {
        return SpringUtil.getBean (UserServiceImpl.class);
    }


}
