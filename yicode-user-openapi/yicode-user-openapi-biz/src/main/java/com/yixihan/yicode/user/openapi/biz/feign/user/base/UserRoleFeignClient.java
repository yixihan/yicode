package com.yixihan.yicode.user.openapi.biz.feign.user.base;

import com.yixihan.yicode.user.api.rest.base.UserRoleApi;
import com.yixihan.yicode.user.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户-角色 FeignClient
 *
 * @author yixihan
 * @date 2022/12/21 10:19
 */
@FeignClient(value = "${feign.client.yicode-user.name}", fallback = FeignExceptionConfig.class)
public interface UserRoleFeignClient extends UserRoleApi {
}
