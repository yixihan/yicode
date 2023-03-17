package com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.sms;

import com.yixihan.yicode.thirdpart.api.rest.sms.SMSApi;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 短信模块 feign client
 *
 * @author yixihan
 * @date 2022/11/23 14:57
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}", fallback = FeignExceptionConfig.class)
public interface SMSFeignClient extends SMSApi {
}
