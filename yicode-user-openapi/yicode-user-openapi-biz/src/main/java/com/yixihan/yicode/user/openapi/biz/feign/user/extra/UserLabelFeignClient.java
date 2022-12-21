package com.yixihan.yicode.user.openapi.biz.feign.user.extra;

import com.yixihan.yicode.user.api.rest.extra.UserLabelApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户标签 FeignClient
 *
 * @author yixihan
 * @date 2022/12/21 13:40
 */
@FeignClient(value = "${feign.client.yicode-user.name}")
public interface UserLabelFeignClient extends UserLabelApi {
}
