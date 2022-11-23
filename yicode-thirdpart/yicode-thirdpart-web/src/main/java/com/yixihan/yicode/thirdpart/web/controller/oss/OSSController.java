package com.yixihan.yicode.thirdpart.web.controller.oss;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.api.dto.request.oss.OSSPolicyDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.oss.OSSUploadDtoReq;
import com.yixihan.yicode.thirdpart.api.reset.oss.OSSApi;
import com.yixihan.yicode.thirdpart.biz.service.oss.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * oss 前端控制器
 *
 * @author yixihan
 * @date 2022-10-24-15:35
 */
@Slf4j
@RestController
public class OSSController implements OSSApi {

    @Resource
    private OSSService ossService;

    @Override
    public ApiResult<CommonDtoResult<String>> uploadFile(OSSUploadDtoReq dtoReq) {
        return ApiResult.create (ossService.uploadFile (dtoReq));
    }

    @Override
    public ApiResult<Map<String, String>> policy(OSSPolicyDtoReq dtoReq) {
        return ApiResult.create (ossService.policy(dtoReq));
    }
}
