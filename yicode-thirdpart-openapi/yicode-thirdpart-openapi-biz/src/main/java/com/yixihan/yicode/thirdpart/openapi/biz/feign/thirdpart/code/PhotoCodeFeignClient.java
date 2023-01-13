package com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.code;

import com.yixihan.yicode.thirdpart.api.rest.code.PhotoCodeApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 图片验证码 feign client
 *
 * @author yixihan
 * @date 2022/11/23 14:56
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}")
public interface PhotoCodeFeignClient extends PhotoCodeApi {
}
