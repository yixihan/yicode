package com.yixihan.yicode.user.openapi.biz.feign.user.base;

import com.yixihan.yicode.user.api.rest.base.UserApi;
import com.yixihan.yicode.user.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户 FeignClient
 *
 * @author yixihan
 * @date 2022-10-22-21:17
 */
@FeignClient(value = "${feign.client.yicode-user.name}", fallback = FeignExceptionConfig.class)
public interface UserFeignClient extends UserApi {
}
