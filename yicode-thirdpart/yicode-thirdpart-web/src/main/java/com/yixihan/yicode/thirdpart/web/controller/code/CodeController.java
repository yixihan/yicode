package com.yixihan.yicode.thirdpart.web.controller.code;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.reset.code.CodeApi;
import com.yixihan.yicode.thirdpart.biz.service.code.CodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码 前端控制器
 *
 * @author yixihan
 * @date 2022/11/21 16:52
 */
@Slf4j
@RestController
public class CodeController implements CodeApi {

    @Resource
    private CodeService codeService;

    @Override
    public void createCode(HttpServletResponse response, String uuid) throws IOException {
        codeService.createCode (response, uuid);
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> validateCode(CodeValidateDtoReq dtoReq) {
        return ApiResult.create (codeService.validateCode (dtoReq));
    }
}
