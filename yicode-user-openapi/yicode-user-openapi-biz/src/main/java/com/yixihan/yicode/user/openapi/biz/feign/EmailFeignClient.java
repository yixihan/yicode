package com.yixihan.yicode.user.openapi.biz.feign;

import com.yixihan.yicode.thirdpart.openapi.api.reset.email.EmailApi;
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
