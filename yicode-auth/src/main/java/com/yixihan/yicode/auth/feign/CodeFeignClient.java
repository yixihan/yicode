package com.yixihan.yicode.auth.feign;

import com.yixihan.yicode.thirdpart.api.reset.code.CodeApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * code FeignClient
 *
 * @author yixihan
 * @date 2022/11/21 17:51
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}")
public interface CodeFeignClient extends CodeApi {
}
