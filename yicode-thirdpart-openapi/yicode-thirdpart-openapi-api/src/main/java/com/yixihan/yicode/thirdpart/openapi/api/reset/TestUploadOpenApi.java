package com.yixihan.yicode.thirdpart.openapi.api.reset;

import com.yixihan.yicode.common.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yixihan
 * @date 2022-10-24-15:33
 */
@Api(tags = "测试 oss")
@RequestMapping("/test/")
public interface TestUploadOpenApi {

    @ApiOperation ("测试 oss 上传字符串")
    @PostMapping(value = "/uploadfile")
    JsonResponse<String> testUploadFile(MultipartFile file);
}
