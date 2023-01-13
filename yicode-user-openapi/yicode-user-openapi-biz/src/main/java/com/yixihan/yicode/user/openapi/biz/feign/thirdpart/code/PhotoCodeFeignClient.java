package com.yixihan.yicode.user.openapi.biz.feign.thirdpart.code;

import com.yixihan.yicode.thirdpart.api.rest.code.PhotoCodeApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * code FeignClient
 *
 * @author yixihan
 * @date 2022/11/21 17:51
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}")
public interface PhotoCodeFeignClient extends PhotoCodeApi {
}
