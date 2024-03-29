package com.yixihan.yicode.thirdpart.open.api.rest.sms;

import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSValidateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 短信模块 OpenApi
 *
 * @author yixihan
 * @date 2022/11/23 13:42
 */
@Api(tags = "短信模块 OpenApi")
@RequestMapping("/open/sms")
public interface SMSOpenApi {

    @ApiOperation("登录-发送短信")
    @PostMapping("/send/mobile/login")
    void loginSend (@RequestBody SMSSendReq req);

    @ApiOperation ("登录-校验短信验证码")
    @PostMapping("/validate/login")
    void loginValidate (@RequestBody SMSValidateReq req);

    @ApiOperation("注册-发送短信")
    @PostMapping("/send/mobile/register")
    void registerSend (@RequestBody SMSSendReq req);

    @ApiOperation ("注册-校验短信验证码")
    @PostMapping("/validate/register")
    void registerValidate (@RequestBody SMSValidateReq req);

    @ApiOperation("重置密码-发送短信")
    @PostMapping("/send/mobile/reset")
    void resetSend (@RequestBody SMSSendReq req);

    @ApiOperation ("重置密码-校验短信验证码")
    @PostMapping("/validate/reset")
    void resetValidate (@RequestBody SMSValidateReq req);

    @ApiOperation("通用-发送短信")
    @PostMapping("/send/mobile/common")
    void commonSend (@RequestBody SMSSendReq req);

    @ApiOperation ("通用-校验短信验证码")
    @PostMapping("/validate/common")
    void commonValidate (@RequestBody SMSValidateReq req);
}
