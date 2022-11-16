package com.yixihan.yicode.runcode.openapi.biz.feign;

import com.yixihan.yicode.runcode.api.rset.QuestionApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 问题 feignClient
 *
 * @author yixihan
 * @date 2022/11/14 10:17
 */
@FeignClient(value = "${feign.client.yicode-runcode.name}")
public interface QuestionFeignClient extends QuestionApi {
}
