package com.yixihan.yicode.auth.feign.thirdpart.code;

import com.yixihan.yicode.thirdpart.api.reset.code.PhotoCodeApi;
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
