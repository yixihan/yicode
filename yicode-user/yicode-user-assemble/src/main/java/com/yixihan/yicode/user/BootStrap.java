package com.yixihan.yicode.user;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.Arrays;

/**
 * 用户模块主启动类
 *
 * @author yixihan
 * @date 2022-10-22-16:19
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
@EnableScheduling
@RefreshScope
@EnableOpenApi
@MapperScan("com.yixihan.yicode.user.dal.mapper")
@SpringBootApplication
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BootStrap.class);
        Environment env = springApplication.run(args).getEnvironment();
        log.info("user modules server has started : {}, CPU core : {}"
                , Arrays.toString(env.getActiveProfiles()), Runtime.getRuntime().availableProcessors());
    }
}