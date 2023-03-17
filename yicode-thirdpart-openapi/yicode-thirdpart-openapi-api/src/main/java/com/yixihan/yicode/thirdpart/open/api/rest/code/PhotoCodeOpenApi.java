package com.yixihan.yicode.thirdpart.open.api.rest.code;

import com.yixihan.yicode.thirdpart.open.api.vo.request.code.CodeValidateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 图形验证码模块 OpenApi
 *
 * @author yixihan
 * @date 2022/11/23 14:21
 */
@Api(tags = "图形验证码 OpenApi")
@RequestMapping("/open/code")
public interface PhotoCodeOpenApi {

    @ApiOperation("生成图形验证码")
    @GetMapping("/create")
    void create(HttpServletResponse response, @RequestParam("uuid") String uuid);

    @ApiOperation ("校验验证码")
    @PostMapping("/validate")
    void validate (@RequestBody CodeValidateReq req);
}
