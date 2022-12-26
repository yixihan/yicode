package com.yixihan.yicode.thirdpart.open.api.reset.code;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * 二维码 openapi
 *
 * @author yixihan
 * @date 2022/12/26 14:38
 */
@Api(tags = "二维码 openapi")
@RequestMapping("/open/qr/")
public interface QrCodeOpenApi {
    
    @ApiOperation("生成二维码")
    @GetMapping("/create")
    void createCode(HttpServletResponse response, @RequestParam("param") String param);
}
