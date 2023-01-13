package com.yixihan.yicode.question.openapi.biz.feign.run;

import com.yixihan.yicode.run.api.rest.CodeRunApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 代码运行 FeignClient
 *
 * @author yixihan
 * @date 2023/1/13 13:29
 */
@FeignClient(value = "${feign.client.yicode-run.name}")
public interface CodeRunFeignClient extends CodeRunApi {
}
