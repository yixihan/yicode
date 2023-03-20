package com.yixihan.yicode.gateway.authorization;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yixihan.yicode.common.component.SecurityContext;
import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.gateway.config.IgnoreUrlsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.*;

/**
 * 鉴权管理器, 用于判断是否有资源的访问权限
 *
 * @author yixihan
 * @date 2022-10-21-17:32
 */
@Slf4j
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange ().getRequest ();
        URI uri = request.getURI ();
        PathMatcher pathMatcher = new AntPathMatcher ();

        // 白名单路径直接放行
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls ();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match (ignoreUrl, uri.getPath ())) {
                return Mono.just (new AuthorizationDecision (true));
            }
        }

        // 对应跨域的预检请求直接放行
        if (request.getMethod () == HttpMethod.OPTIONS) {
            return Mono.just (new AuthorizationDecision (true));
        }

        // 校验权限
        String token = request.getHeaders ().getFirst (AuthConstant.JWT_TOKEN_HEADER);
        if (token == null || StrUtil.isEmpty (token)) {
            return Mono.just (new AuthorizationDecision (false));
        }
        SecurityContext context = new SecurityContext (token.substring (AuthConstant.TOKEN_SUB_INDEX));
        redisTemplate.opsForHash ().put (AuthConstant.USER_MAP_KEY, context.getToken (), JSONUtil.toJsonStr (context.getUser ()));

        // 获取当前访问方法的使用角色权限
        Map<Object, Object> methodRolesMap = redisTemplate.opsForHash().entries(AuthConstant.METHOD_ROLE_MAP_KEY);
        Iterator<Object> iterator = methodRolesMap.keySet().iterator();
        List<String> authorities = new ArrayList<> ();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, uri.getPath())) {
                authorities.addAll(Convert.toList(String.class, methodRolesMap.get(pattern)));
            }
        }
        log.info ("当前访问方法 : {}, 访问需有使用权限 : {}", uri.getPath (), authorities);

        // 认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter (Authentication::isAuthenticated)
                .flatMapIterable ((Authentication authentication) -> {
                    Collection<? extends GrantedAuthority> roleList = authentication.getAuthorities ();
                    log.info ("当前用户拥有角色 : {}", roleList);
                    return roleList;
                })
                .map (GrantedAuthority::getAuthority)
                .any (o -> {
                    boolean flag = CollectionUtil.isEmpty (authorities) || authorities.contains (o);
                    log.info ("是否具有访问权限 : {}", flag);
                    return flag;
                })
                .map (AuthorizationDecision::new)
                .defaultIfEmpty (new AuthorizationDecision (false));
    }

}

