package com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.email;

import com.yixihan.yicode.thirdpart.api.rest.email.EmailApi;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 邮件模块 feign client
 *
 * @author yixihan
 * @date 2022/11/23 14:56
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}", fallback = FeignExceptionConfig.class)
public interface EmailFeignClient extends EmailApi {
}
