package com.yixihan.yicode.auth.config;

import com.yixihan.yicode.auth.component.JwtTokenEnhancer;
import com.yixihan.yicode.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yixihan
 * @date 2022-10-20-16:28
 */
@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private final PasswordEncoder passwordEncoder;

    @Resource
    private final UserService userService;

    @Resource
    private final AuthenticationManager authenticationManager;

    @Resource
    private final JwtTokenEnhancer jwtTokenEnhancer;

    @Resource
    private Oauth2ServerProperties properties;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory ()
                .withClient (properties.getClientId ())
                .secret (passwordEncoder.encode (properties.getRawPassword ()))
                .scopes (properties.getScopes ())
                .authorizedGrantTypes ("password", "refresh_token", "authorization_code", "sms")
                .accessTokenValiditySeconds (properties.getAccessTokenValiditySeconds ())
                .refreshTokenValiditySeconds (properties.getRefreshTokenValiditySeconds ());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain ();
        List<TokenEnhancer> delegates = new ArrayList<> ();
        delegates.add (jwtTokenEnhancer);
        delegates.add (accessTokenConverter ());
        //配置JWT的内容增强器
        enhancerChain.setTokenEnhancers (delegates);
        endpoints.authenticationManager (authenticationManager)
                //配置加载用户信息的服务
                .userDetailsService (userService)
                .accessTokenConverter (accessTokenConverter ())
                .tokenEnhancer (enhancerChain);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter ();
        jwtAccessTokenConverter.setKeyPair (keyPair ());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory (
                new ClassPathResource (properties.getFileName ()),
                "yicode".toCharArray ()
        );
        return keyStoreKeyFactory.getKeyPair ("jwt", properties.getPassword ().toCharArray ());
    }
}
