package com.yixihan.yicode.thirdpart.openapi.api.reset.oss;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.OSSUploadDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
