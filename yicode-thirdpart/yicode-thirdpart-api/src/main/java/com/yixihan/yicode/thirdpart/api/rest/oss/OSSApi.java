package com.yixihan.yicode.thirdpart.api.rest.oss;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.api.dto.request.oss.OSSPolicyDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * oss模块 api
 *
 * @author yixihan
 * @date 2022-10-24-15:33
 */
@Api(tags = "oss模块 api")
@RequestMapping("/oss")
public interface OSSApi {

    @ApiOperation (value = "获取上传密钥")
    @PostMapping(value = "/upload/policy", produces = "application/json")
    ApiResult<Map<String, String>> policy (@RequestBody OSSPolicyDtoReq dtoReq);
}
