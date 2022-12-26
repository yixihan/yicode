package com.yixihan.yicode.thirdpart.openapi.web.controller.code;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.open.api.reset.code.PhotoCodeOpenApi;
import com.yixihan.yicode.thirdpart.open.api.vo.request.code.CodeValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.service.code.PhotoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片验证码 前端控制器
 *
 * @author yixihan
 * @date 2022/11/23 14:44
 */
@Slf4j
@RestController
public class PhotoCodeController implements PhotoCodeOpenApi {

    @Resource
    private PhotoCodeService service;

    @Override
    public void createCode(HttpServletResponse response, String uuid) throws IOException {
        service.createCode (response, uuid);
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> validateCode(CodeValidateReq req) {
        return JsonResponse.ok (service.validateCode (req));
    }
}
