package com.yixihan.yicode.thirdpart.openapi.web.controller.code;

import com.yixihan.yicode.thirdpart.open.api.reset.code.QrCodeOpenApi;
import com.yixihan.yicode.thirdpart.openapi.biz.service.code.QrCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 二维码 前端控制器
 *
 * @author yixihan
 * @date 2022/12/26 14:40
 */
@Slf4j
@RestController
public class QrCodeController implements QrCodeOpenApi {
    
    @Resource
    private QrCodeService service;
    
    @Override
    public void createCode(HttpServletResponse response, String param) {
        service.createCode (response, param);
    }
}
