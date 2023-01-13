package com.yixihan.yicode.user.openapi.biz.feign.thirdpart.email;

import com.yixihan.yicode.thirdpart.api.rest.email.EmailApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * email feignClient
 *
 * @author yixihan
 * @date 2022/11/8 10:14
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}")
public interface EmailFeignClient extends EmailApi {
}
