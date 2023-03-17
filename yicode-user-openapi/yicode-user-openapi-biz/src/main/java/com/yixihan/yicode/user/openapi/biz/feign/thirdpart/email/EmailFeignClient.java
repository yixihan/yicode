package com.yixihan.yicode.user.openapi.biz.feign.thirdpart.email;

import com.yixihan.yicode.thirdpart.api.rest.email.EmailApi;
import com.yixihan.yicode.user.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * email feignClient
 *
 * @author yixihan
 * @date 2022/11/8 10:14
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}", fallback = FeignExceptionConfig.class)
public interface EmailFeignClient extends EmailApi {
}
