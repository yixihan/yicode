package com.yixihan.yicode.auth.feign.thirdpart.sms;

import com.yixihan.yicode.thirdpart.api.rest.sms.SMSApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * sms feignClient
 *
 * @author yixihan
 * @date 2022/11/8 10:06
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}")
public interface SMSFeignClient extends SMSApi {

}
