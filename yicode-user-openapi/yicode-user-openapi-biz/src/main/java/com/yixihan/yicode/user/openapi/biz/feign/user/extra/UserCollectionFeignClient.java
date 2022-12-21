package com.yixihan.yicode.user.openapi.biz.feign.user.extra;

import com.yixihan.yicode.user.api.rest.extra.UserCollectionApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户收藏 FeignClient
 *
 * @author yixihan
 * @date 2022/12/21 11:20
 */
@FeignClient(value = "${feign.client.yicode-user.name}")
public interface UserCollectionFeignClient extends UserCollectionApi {
}
