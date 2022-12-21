package com.yixihan.yicode.user.openapi.biz.feign.user.extra;

import com.yixihan.yicode.user.api.rest.extra.UserFavoriteApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户收藏夹 FeignClient
 *
 * @author yixihan
 * @date 2022/12/21 10:21
 */
@FeignClient(value = "${feign.client.yicode-user.name}")
public interface UserFavoriteFeignClient extends UserFavoriteApi {
}
