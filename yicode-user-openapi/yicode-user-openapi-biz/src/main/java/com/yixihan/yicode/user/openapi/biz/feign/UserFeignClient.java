package com.yixihan.yicode.user.openapi.biz.feign;

import com.yixihan.yicode.user.api.rest.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户 FeignClient
 *
 * @author yixihan
 * @date 2022-10-22-21:17
 */
@FeignClient(value = "${feign.client.yicode-user.name}")
public interface UserFeignClient extends UserApi {
}
