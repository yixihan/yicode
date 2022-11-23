package com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.email;

import com.yixihan.yicode.thirdpart.open.api.reset.email.EmailOpenApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 邮件模块 feign client
 *
 * @author yixihan
 * @date 2022/11/23 14:56
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}")
public interface EmailFeignClient extends EmailOpenApi {
}
