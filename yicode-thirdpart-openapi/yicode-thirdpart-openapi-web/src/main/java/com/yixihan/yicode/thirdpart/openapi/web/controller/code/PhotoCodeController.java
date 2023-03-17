package com.yixihan.yicode.thirdpart.openapi.web.controller.code;

import com.yixihan.yicode.thirdpart.open.api.rest.code.PhotoCodeOpenApi;
import com.yixihan.yicode.thirdpart.open.api.vo.request.code.CodeValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.service.code.PhotoCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    public void create(HttpServletResponse response, String uuid) {
        service.create (response, uuid);
    }

    @Override
    public void validate(CodeValidateReq req) {
        service.validate (req);
    }
}
