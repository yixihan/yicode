package com.yixihan.yicode.question.openapi.biz.feign.question.label;

import com.yixihan.yicode.question.api.rest.label.LabelUserApi;
import com.yixihan.yicode.question.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户标签 FeignClient
 *
 * @author yixihan
 * @date 2023/1/16 14:49
 */
@FeignClient(value = "${feign.client.yicode-question.name}", fallback = FeignExceptionConfig.class)
public interface LabelUserFeignClient extends LabelUserApi {
}
