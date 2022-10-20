package com.yixihan.yicode.auth.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.Arrays;

/**
 * 认证授权服务端主启动类
 *
 * @author yixihan
 * @date 2022-10-18-17:10
 */
@Slf4j
@EnableDiscoveryClient
@EnableCaching
@EnableScheduling
@RefreshScope
@EnableOpenApi
@SpringBootApplication
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication (BootStrap.class);
        Environment env = springApplication.run (args).getEnvironment ();
        log.info ("auth-server modules server has started : {}, CPU core : {}", Arrays.toString (env.getActiveProfiles ()), Runtime.getRuntime ().availableProcessors ());
    }
}
