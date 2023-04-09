package com.yixihan.yicode.question.openapi.biz.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * description
 *
 * @author yixihan
 * @date 2023/4/9 14:56
 */
@Component
@Slf4j
public class FeignConfig implements RequestInterceptor {

    public static final String VALUE = "X-Request-No";

    public FeignConfig() {
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 1000, 5);
    }

    @Bean
    public RequestContextListener requestContextListenerBean() {
        return new RequestContextListener();
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            RequestContextHolder.setRequestAttributes(requestAttributes, true);
            try {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            } catch (Exception e) {
                log.info("定时任务介入，授权异常");
            }

        } else {
            RequestContextHolder.setRequestAttributes(new NonWebRequestAttributes(), Boolean.TRUE);
            HttpServletRequest httpRequest = this.getHttpServletRequestSafely();

            if (null != httpRequest && null != httpRequest.getAttribute(VALUE)) {
                requestTemplate.header(VALUE, httpRequest.getAttribute(VALUE).toString());
            }
        }

    }

    public HttpServletRequest getHttpServletRequestSafely() {
        try {
            RequestAttributes requestAttributesSafely = this.getRequestAttributesSafely();
            return requestAttributesSafely instanceof NonWebRequestAttributes ? null : ((ServletRequestAttributes) requestAttributesSafely).getRequest();
        } catch (Exception var2) {
            return null;
        }
    }

    public RequestAttributes getRequestAttributesSafely() {
        RequestAttributes requestAttributes;
        try {
            requestAttributes = RequestContextHolder.currentRequestAttributes();
        } catch (IllegalStateException var3) {
            requestAttributes = new NonWebRequestAttributes();
        }

        return requestAttributes;
    }
}


