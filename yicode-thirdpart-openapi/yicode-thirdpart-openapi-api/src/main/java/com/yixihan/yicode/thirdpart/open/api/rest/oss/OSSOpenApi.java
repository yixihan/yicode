package com.yixihan.yicode.thirdpart.open.api.rest.oss;

import com.yixihan.yicode.common.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * oss模块 OpenApi
 *
 * @author yixihan
 * @date 2022/11/23 13:52
 */
@Api(tags = "oss模块 OpenApi")
@RequestMapping("/open/oss")
public interface OSSOpenApi {

    @ApiOperation (value = "获取上传密钥")
    @PostMapping(value = "/upload/policy")
    JsonResponse<Map<String, String>> policy ();
}
