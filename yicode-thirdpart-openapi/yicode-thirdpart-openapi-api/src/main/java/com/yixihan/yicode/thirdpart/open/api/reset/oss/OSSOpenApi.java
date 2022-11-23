package com.yixihan.yicode.thirdpart.open.api.reset.oss;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.open.api.vo.request.oss.OSSUploadReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * oss模块 openApi
 *
 * @author yixihan
 * @date 2022/11/23 13:52
 */
@Api(tags = "oss模块 openApi")
@RequestMapping("/open/oss/")
public interface OSSOpenApi {

    @ApiOperation(value = "上传文件", notes = "商城文件路径 : [userId/fileDir/fileName.fileSuffix]")
    @PostMapping(value = "/upload/file")
    JsonResponse<CommonVO<String>> uploadFile(OSSUploadReq req);

    @ApiOperation (value = "获取上传密钥")
    @PostMapping(value = "/upload/policy")
    JsonResponse<Map<String, String>> policy ();

}
