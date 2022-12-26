package com.yixihan.yicode.thirdpart.open.api.reset.code;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.open.api.vo.request.code.CodeValidateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图形验证码模块 openApi
 *
 * @author yixihan
 * @date 2022/11/23 14:21
 */
@Api(tags = "图形验证码 openApi")
@RequestMapping("/open/code/")
public interface CodeOpenApi {

    @ApiOperation("生成图形验证码")
    @GetMapping("/create")
    void createCode(HttpServletResponse response, @RequestParam("uuid") String uuid) throws IOException;

    @ApiOperation ("校验验证码")
    @PostMapping("/validate")
    JsonResponse<CommonVO<Boolean>> validateCode (@RequestBody CodeValidateReq req);
}
