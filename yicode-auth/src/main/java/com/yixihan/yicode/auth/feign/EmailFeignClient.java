package com.yixihan.yicode.auth.feign;

import com.yixihan.yicode.thirdpart.openapi.api.reset.email.EmailSendApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * email feignClient
 *
 * @author yixihan
 * @date 2022/11/8 10:14
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}")
public interface EmailFeignClient extends EmailSendApi {
}
