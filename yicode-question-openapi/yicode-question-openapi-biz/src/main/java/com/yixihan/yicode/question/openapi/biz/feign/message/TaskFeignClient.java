package com.yixihan.yicode.question.openapi.biz.feign.message;

import com.yixihan.yicode.message.api.rest.TaskApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 任务队列 FeignClient
 *
 * @author yixihan
 * @date 2023/1/10 17:13
 */
@FeignClient(value = "${feign.client.yicode-message.name}")
public interface TaskFeignClient extends TaskApi {
}
