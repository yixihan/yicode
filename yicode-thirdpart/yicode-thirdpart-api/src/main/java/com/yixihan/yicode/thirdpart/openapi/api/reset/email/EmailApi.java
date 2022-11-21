package com.yixihan.yicode.thirdpart.openapi.api.reset.email;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailValidateDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 邮件发送 api
 *
 * @author yixihan
 * @date 2022-10-24-20:07
 */
@Api(tags = "邮件发送")
@RequestMapping("/email/")
public interface EmailApi {

    @ApiOperation ("/发送邮件")
    @PostMapping("/sendemail")
    ApiResult<CommonDtoResult<Boolean>> sendEmail(@RequestBody EmailSendDtoReq req);

    @ApiOperation ("校验验证码")
    @PostMapping("/validate")
    ApiResult<CommonDtoResult<Boolean>> validate (@RequestBody EmailValidateDtoReq dtoReq);
}
