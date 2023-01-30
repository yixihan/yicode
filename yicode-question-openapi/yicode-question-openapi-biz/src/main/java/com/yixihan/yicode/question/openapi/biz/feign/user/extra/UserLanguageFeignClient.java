package com.yixihan.yicode.question.openapi.biz.feign.user.extra;

import com.yixihan.yicode.user.api.rest.extra.UserLanguageApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户语言 FeignClient
 *
 * @author yixihan
 * @date 2022/12/21 13:40
 */
@FeignClient(value = "${feign.client.yicode-user.name}")
public interface UserLanguageFeignClient extends UserLanguageApi {
}
