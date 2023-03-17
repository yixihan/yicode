package com.yixihan.yicode.question.openapi.biz.feign.question.question;

import com.yixihan.yicode.question.api.rest.question.QuestionApi;
import com.yixihan.yicode.question.openapi.biz.feign.config.FeignExceptionConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 问题 FeignClient
 *
 * @author yixihan
 * @date 2023/1/13 12:38
 */
@FeignClient(value = "${feign.client.yicode-question.name}", fallback = FeignExceptionConfig.class)
public interface QuestionFeignClient extends QuestionApi {
}
