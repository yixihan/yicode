package com.yixihan.yicode.user.openapi.biz.feign.thirdpart.sms;

import com.yixihan.yicode.thirdpart.api.rest.sms.SMSApi;
import com.yixihan.yicode.user.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * sms feignClient
 *
 * @author yixihan
 * @date 2022/11/8 10:06
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}", fallback = FeignExceptionConfig.class)
public interface SMSFeignClient extends SMSApi {

}
