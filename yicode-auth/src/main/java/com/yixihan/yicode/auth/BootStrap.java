package com.yixihan.yicode.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

/**
 * yicode auth 主启动类
 *
 * @author yixihan
 * @date 2022-10-20-15:15
 */
@Slf4j
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@RefreshScope
@EnableCaching
@SpringBootApplication
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication (BootStrap.class);
        Environment env = springApplication.run (args).getEnvironment ();
        log.info ("auth modules server has started : {}, CPU core : {}",
                Arrays.toString (env.getActiveProfiles ()), Runtime.getRuntime ().availableProcessors ());
    }
}
