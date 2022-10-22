package com.yixihan.yicode.gateway.authorization;

import cn.hutool.core.convert.Convert;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * 鉴权管理器, 用于判断是否有资源的访问权限
 *
 * @author yixihan
 * @date 2022-10-21-17:32
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        //从Redis中获取当前路径可访问角色列表
        Object obj = redisTemplate.opsForValue ().get ("userRoleList");
        List<String> authorities = Convert.toList(String.class,obj);
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}

