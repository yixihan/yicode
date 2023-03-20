package com.yixihan.yicode.gateway.filter;

import com.yixihan.yicode.gateway.config.IgnoreUrlsConfig;
import lombok.NonNull;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * 白名单路径访问时需要移除 jwt 请求头
 *
 * @author yixihan
 * @date 2022-10-21-17:36
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {
    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;


    @Override
    @NonNull
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest ();
        URI uri = request.getURI ();
        PathMatcher pathMatcher = new AntPathMatcher ();
        // 白名单路径移除 jwt 请求头
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls ();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match (ignoreUrl, uri.getPath ())) {
                request = exchange.getRequest ().mutate ().header ("Authorization", "").build ();
                exchange = exchange.mutate ().request (request).build ();
                return chain.filter (exchange);
            }
        }
        return chain.filter (exchange);
    }
}
