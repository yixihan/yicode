package com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.oss;

import com.yixihan.yicode.thirdpart.api.reset.oss.OSSApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * oss 模块 feign client
 *
 * @author yixihan
 * @date 2022/11/23 14:57
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}")
public interface OSSFeignClient extends OSSApi {
}
