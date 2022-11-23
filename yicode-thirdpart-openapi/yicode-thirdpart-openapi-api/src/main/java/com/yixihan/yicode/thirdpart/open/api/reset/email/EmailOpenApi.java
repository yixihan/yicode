package com.yixihan.yicode.thirdpart.open.api.reset.email;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailValidateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 邮件模块 openApi
 *
 * @author yixihan
 * @date 2022/11/23 14:11
 */
@Api(tags = "邮件模块 openApi")
@RequestMapping("/open/email/")
public interface EmailOpenApi {

    @ApiOperation("登录-发送邮件")
    @PostMapping("/send/email/login")
    JsonResponse<CommonVO<Boolean>> loginSend (@RequestBody EmailSendReq req);

    @ApiOperation ("登录-校验邮件验证码")
    @PostMapping("/validate/login")
    JsonResponse<CommonVO<Boolean>> loginValidate (@RequestBody EmailValidateReq req);

    @ApiOperation("注册-发送邮件")
    @PostMapping("/send/email/register")
    JsonResponse<CommonVO<Boolean>> registerSend (@RequestBody EmailSendReq req);

    @ApiOperation ("注册-校验邮件验证码")
    @PostMapping("/validate/register")
    JsonResponse<CommonVO<Boolean>> registerValidate (@RequestBody EmailValidateReq req);

    @ApiOperation("重置密码-发送邮件")
    @PostMapping("/send/email/reset")
    JsonResponse<CommonVO<Boolean>> resetSend (@RequestBody EmailSendReq req);

    @ApiOperation ("重置密码-校验邮件验证码")
    @PostMapping("/validate/reset")
    JsonResponse<CommonVO<Boolean>> resetValidate (@RequestBody EmailValidateReq req);

    @ApiOperation("通用-发送邮件")
    @PostMapping("/send/email/common")
    JsonResponse<CommonVO<Boolean>> commonSend (@RequestBody EmailSendReq req);

    @ApiOperation ("通用-校验邮件验证码")
    @PostMapping("/validate/common")
    JsonResponse<CommonVO<Boolean>> commonValidate (@RequestBody EmailValidateReq req);
}
