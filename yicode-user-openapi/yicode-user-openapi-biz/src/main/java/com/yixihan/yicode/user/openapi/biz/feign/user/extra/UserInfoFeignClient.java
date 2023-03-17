package com.yixihan.yicode.user.openapi.biz.feign.user.extra;

import com.yixihan.yicode.user.api.rest.extra.UserInfoApi;
import com.yixihan.yicode.user.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户资料 FeignClient
 *
 * @author yixihan
 * @date 2022/12/21 10:20
 */
@FeignClient(value = "${feign.client.yicode-user.name}", fallback = FeignExceptionConfig.class)
public interface UserInfoFeignClient extends UserInfoApi {
}
