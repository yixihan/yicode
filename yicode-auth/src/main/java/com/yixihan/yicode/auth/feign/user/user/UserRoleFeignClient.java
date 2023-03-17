package com.yixihan.yicode.auth.feign.user.user;

import com.yixihan.yicode.auth.config.FeignExceptionConfig;
import com.yixihan.yicode.user.api.rest.base.UserRoleApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * userRole feignClient
 *
 * @author yixihan
 * @date 2022-10-23-13:14
 */
@FeignClient(value = "${feign.client.yicode-user.name}", fallback = FeignExceptionConfig.class)
public interface UserRoleFeignClient extends UserRoleApi {
}
