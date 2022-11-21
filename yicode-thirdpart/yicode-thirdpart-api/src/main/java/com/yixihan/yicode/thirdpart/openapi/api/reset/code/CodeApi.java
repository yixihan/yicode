package com.yixihan.yicode.thirdpart.openapi.api.reset.code;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.CodeValidateDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码 api
 *
 * @author yixihan
 * @date 2022/11/21 16:45
 */
@Api(tags = "验证码发送")
@RequestMapping("/code/")
public interface CodeApi {

    @ApiOperation("/生成验证码")
    @PostMapping("/create/code/{uuid}")
    void createCode(HttpServletResponse response, @PathVariable("uuid") String uuid) throws IOException;

    @ApiOperation ("校验验证码")
    @PostMapping("/validate")
    ApiResult<CommonDtoResult<Boolean>> validateCode (@RequestBody CodeValidateDtoReq dtoReq);
}
