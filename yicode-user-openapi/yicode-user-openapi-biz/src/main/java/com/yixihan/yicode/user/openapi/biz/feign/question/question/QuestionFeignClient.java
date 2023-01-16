package com.yixihan.yicode.user.openapi.biz.feign.question.question;

import com.yixihan.yicode.question.api.rest.question.QuestionApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 问题 FeignClient
 *
 * @author yixihan
 * @date 2023/1/13 12:38
 */
@FeignClient(value = "${feign.client.yicode-question.name}")
public interface QuestionFeignClient extends QuestionApi {
}
