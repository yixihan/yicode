package com.yixihan.yicode.thirdpart.api.reset.oss;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.api.dto.request.OSSPolicyDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.OSSUploadDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * oss api
 *
 * @author yixihan
 * @date 2022-10-24-15:33
 */
@Api(tags = "oss api")
@RequestMapping("/oss/")
public interface OSSApi {

    @ApiOperation (value = "上传文件", notes = "商城文件路径 : [userId/fileDir/fileName.fileSuffix]")
    @PostMapping(value = "/upload/file")
    JsonResponse<CommonDtoResult<String>> testUploadFile(OSSUploadDtoReq dtoReq);

    @ApiOperation (value = "获取上传密钥")
    @PostMapping(value = "/upload/policy", produces = "application/json")
    JsonResponse<Map<String, String>> policy (@RequestBody OSSPolicyDtoReq dtoReq);
}
