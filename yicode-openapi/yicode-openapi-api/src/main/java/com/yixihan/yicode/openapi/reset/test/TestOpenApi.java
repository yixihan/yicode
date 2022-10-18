package com.yixihan.yicode.openapi.reset.test;

import com.yixihan.yicode.common.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yixihan
 * @date 2022-10-14-13:35
 */
@Api("test")
@RequestMapping("/open/test")
public interface TestOpenApi {

    @ApiModelProperty("测试获取 yaml 配置")
    @PostMapping(value = "/testGetYaml", produces = "application/json")
    JsonResponse<String> testGetYaml ();

    @ApiModelProperty("测试服务限流")
    @PostMapping(value = "/testFlow", produces = "application/json")
    JsonResponse<String> testFlow ();


    @ApiModelProperty("测试服务降级")
    @PostMapping(value = "/testDegrade", produces = "application/json")
    JsonResponse<String> testDegrade();
}
