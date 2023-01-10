package com.yixihan.yicode.user.openapi.biz.feign.message;

import com.yixihan.yicode.message.api.reset.MessageApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 消息系统 FeignClient
 *
 * @author yixihan
 * @date 2023/1/10 17:13
 */
@FeignClient(value = "${feign.client.yicode-message.name}")
public interface MessageFeignClient extends MessageApi {
}
