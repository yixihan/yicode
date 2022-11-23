package com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.sms;

import com.yixihan.yicode.thirdpart.api.reset.sms.SMSApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 短信模块 feign client
 *
 * @author yixihan
 * @date 2022/11/23 14:57
 */
@FeignClient(value = "${feign.client.yicode-thirdpart.name}")
public interface SMSFeignClient extends SMSApi {
}
