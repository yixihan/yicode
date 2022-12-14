package com.yixihan.yicode.user.openapi.biz.feign.user.msg;

import com.yixihan.yicode.user.api.rest.msg.UserMsgApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户信息 FeignClient
 *
 * @author yixihan
 * @date 2022/12/21 10:21
 */
@FeignClient(value = "${feign.client.yicode-user.name}")
public interface UserMsgFiegnClient extends UserMsgApi {
}
