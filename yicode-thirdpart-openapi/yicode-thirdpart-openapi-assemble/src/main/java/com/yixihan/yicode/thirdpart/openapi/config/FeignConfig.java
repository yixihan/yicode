package com.yixihan.yicode.thirdpart.openapi.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign 日志配置类
 *
 * @author yixihan
 * @date 2022/11/23 17:47
 */
@Configuration
public class FeignConfig {

    /**
     * 描述:注册一个设定日志内容级别的日志
     * <p>
     * # NONE:不记录日志(默认);
     * # BASIC:只记录请求方法和URL以及响应状态代码和执行时间;
     * # HEADERS:记录请求和应答的头的基本信息;
     * # FULL:记录请求和响应的头信息,正文和元数据;
     *
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
