package com.yixihan.yicode.question.openapi.biz.feign.question.label;

import com.yixihan.yicode.question.api.rest.label.LabelQuestionApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 标签-问题 FeignClient
 *
 * @author yixihan
 * @date 2023/1/13 12:38
 */
@FeignClient(value = "${feign.client.yicode-question.name}")
public interface LabelQuestionFeignClient extends LabelQuestionApi {
}
