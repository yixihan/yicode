package com.yixihan.yicode.question.openapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger-ui 配置类
 *
 * @author yixihan
 * @date 2023/3/17 9:05
 */
@Configuration
public class SwaggerConfig {
    
    @Value ("${yicode.base-package}")
    private String basePackage;
    
    /**
     * 配置 Swagger 的 Docket 实例
     */
    @Bean
    public Docket docket() {
        return new Docket (DocumentationType.OAS_30)
                // 限制只搜索指定包下的 api
                .select ()
                .apis (RequestHandlerSelectors.basePackage (basePackage))
                .build ()
                // 创建该 API 的基本信息
                .apiInfo (apiInfo ());
        
    }
    
    
    /**
     * 配置 Swagger 的信息
     *
     * @return apiInfo
     */
    private ApiInfo apiInfo() {
        
        // 作者信息
        Contact authorInfo = new Contact ("yixihan", "https://github.com/yixihan", "3113788997@qq.com");
        
        return new ApiInfoBuilder ()
                // 设置标题
                .title ("yicode-question-openapi - swagger 接口文档")
                // 设置描述
                .description ("yicode")
                // 设置作者信息
                .contact (authorInfo)
                .build ();
    }
}
