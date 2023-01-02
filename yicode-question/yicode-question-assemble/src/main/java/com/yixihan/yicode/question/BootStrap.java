package com.yixihan.yicode.question;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.Arrays;

/**
 * yicode question 主启动类
 *
 * @author yixihan
 * @date 2023/1/2 13:36
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
@EnableScheduling
@RefreshScope
@EnableOpenApi
@EnableTransactionManagement
@SpringBootApplication
public class BootStrap {
    
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BootStrap.class);
        Environment env = springApplication.run(args).getEnvironment();
        log.info("question modules server has started : {}, CPU core : {}",
                Arrays.toString(env.getActiveProfiles()), Runtime.getRuntime().availableProcessors());
    }
}
