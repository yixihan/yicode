package com.yixihan.yicode.question.openapi.biz.feign.question.question;

import com.yixihan.yicode.question.api.rest.question.QuestionAnswerApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 问题-答案 FeignClient
 *
 * @author yixihan
 * @date 2023/1/13 12:38
 */
@FeignClient(value = "${feign.client.yicode-question.name}")
public interface QuestionAnswerFeignClient extends QuestionAnswerApi {
}
