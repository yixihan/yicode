package com.yixihan.yicode.auth.feign.user.user;

import com.yixihan.yicode.user.api.rest.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * user feignClient
 *
 * @author yixihan
 * @date 2022-10-23-13:18
 */
@FeignClient(value = "${feign.client.yicode-user.name}")
public interface UserFeignClient extends UserApi {
}
