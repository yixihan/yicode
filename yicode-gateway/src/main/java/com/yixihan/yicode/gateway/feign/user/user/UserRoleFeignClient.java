package com.yixihan.yicode.gateway.feign.user.user;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * userRole FeignClient
 *
 * @author yixihan
 * @date 2022-10-23-13:51
 */
@FeignClient(value = "${feign.client.yicode-user.name}")
public interface UserRoleFeignClient {
}
