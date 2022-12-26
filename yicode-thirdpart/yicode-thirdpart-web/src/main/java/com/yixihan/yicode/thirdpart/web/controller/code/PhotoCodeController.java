package com.yixihan.yicode.thirdpart.web.controller.code;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.reset.code.PhotoCodeApi;
import com.yixihan.yicode.thirdpart.biz.service.code.PhotoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 验证码 前端控制器
 *
 * @author yixihan
 * @date 2022/11/21 16:52
 */
@Slf4j
@RestController
public class PhotoCodeController implements PhotoCodeApi {

    @Resource
    private PhotoCodeService service;

    @Override
    public void createCode(String code, String uuid){
        service.createCode (code, uuid);
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> validateCode(CodeValidateDtoReq dtoReq) {
        return ApiResult.create (service.validateCode (dtoReq));
    }
}
