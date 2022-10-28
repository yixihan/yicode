package com.yixihan.yicode.thirdpart.openapi;

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
 * yicode thirdpart openapi 主启动类
 * @author yixihan
 * @date 2022-10-24-13:50
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
@EnableScheduling
@RefreshScope
@EnableOpenApi
@MapperScan("com.yixihan.yicode.thirdpart.dal.mapper")
@SpringBootApplication
public class BootStrap {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BootStrap.class);
        Environment env = springApplication.run(args).getEnvironment();
        log.info("openapi modules server has started : {}, CPU core : {}"
                , Arrays.toString(env.getActiveProfiles()), Runtime.getRuntime().availableProcessors());
    }
}
