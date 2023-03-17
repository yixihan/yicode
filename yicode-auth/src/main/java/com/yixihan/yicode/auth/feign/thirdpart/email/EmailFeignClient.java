package com.yixihan.yicode.auth.feign.thirdpart.email;

import com.yixihan.yicode.auth.config.FeignExceptionConfig;
import com.yixihan.yicode.thirdpart.api.rest.email.EmailApi;
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
