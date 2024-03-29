package com.yixihan.yicode.gateway.config;

import cn.hutool.core.collection.CollUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * description
 *
 * @author yixihan
 * @date 2022/11/16 18:01
 */
@Component("corsResponseHeaderFilter")
public class CorsResponseHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter (exchange).then (Mono.defer (() -> {
            exchange.getResponse ().getHeaders ().entrySet ().stream ()
                    .filter (kv -> (kv.getValue () != null && kv.getValue ().size () > 1))
                    .filter (kv -> (kv.getKey ().equals (HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN) ||
                                    kv.getKey ().equals (HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS)))
                    .forEach (kv -> kv.setValue (CollUtil.newArrayList (kv.getValue ().get (0))));

            return chain.filter (exchange);
        }));
    }

    @Override
    public int getOrder() {
        // 指定此过滤器位于NettyWriteResponseFilter之后
        // 即待处理完响应体后接着处理响应头
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1;
    }
}

