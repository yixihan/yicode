package com.yixihan.yicode.openapi.reset.test;

import com.yixihan.yicode.common.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description
 *
 * @author yixihan
 * @date 2022/11/24 10:12
 */
@Api(tags = "测试异常捕获 openApi")
@RequestMapping("/open/exception/")
public interface TestExceptionOpenApi {
    
    @ApiOperation ("测试 BizException")
    @PostMapping("/biz")
    JsonResponse<String> testBizException ();

    @ApiOperation ("测试 RRException")
    @PostMapping("/rre")
    JsonResponse<String> testRRException ();

    @ApiOperation ("测试 BindException")
    @PostMapping("/bind")
    JsonResponse<String> testBindException ();

    @ApiOperation ("测试 NPE")
    @PostMapping("/null")
    JsonResponse<String> testNullPointerException ();

    @ApiOperation ("测试 Exception")
    @PostMapping("/exception")
    JsonResponse<String> testException ();
}
