package com.yixihan.yicode.question.openapi.biz.feign.question.label;

import com.yixihan.yicode.question.api.rest.label.LabelUserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户标签 FeignClient
 *
 * @author yixihan
 * @date 2023/1/16 14:49
 */
@FeignClient(value = "${feign.client.yicode-question.name}")
public interface LabelUserFeignClient extends LabelUserApi {
}
