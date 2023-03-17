package com.yixihan.yicode.user.openapi.biz.feign.user.extra;

import com.yixihan.yicode.user.api.rest.extra.UserWebsiteApi;
import com.yixihan.yicode.user.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户个人网址 FeignClient
 *
 * @author yixihan
 * @date 2022/12/21 13:39
 */
@FeignClient(value = "${feign.client.yicode-user.name}", fallback = FeignExceptionConfig.class)
public interface UserWebsiteFeignClient extends UserWebsiteApi {
}
