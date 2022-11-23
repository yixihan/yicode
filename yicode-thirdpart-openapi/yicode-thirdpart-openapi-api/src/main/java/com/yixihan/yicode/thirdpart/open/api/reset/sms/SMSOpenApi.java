package com.yixihan.yicode.thirdpart.open.api.reset.sms;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSValidateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 短信模块 openApi
 *
 * @author yixihan
 * @date 2022/11/23 13:42
 */
@Api(tags = "短信模块 openApi")
@RequestMapping("/open/sms/")
public interface SMSOpenApi {

    @ApiOperation("登录-发送短信")
    @PostMapping("/login/send/mobile")
    JsonResponse<CommonVO<Boolean>> loginSend (@RequestBody SMSSendReq req);

    @ApiOperation ("登录-校验短信验证码")
    @PostMapping("/login/validate")
    JsonResponse<CommonVO<Boolean>> loginValidate (@RequestBody SMSValidateReq req);

    @ApiOperation("注册-发送短信")
    @PostMapping("/register/send/mobile")
    JsonResponse<CommonVO<Boolean>> registerSend (@RequestBody SMSSendReq req);

    @ApiOperation ("注册-校验短信验证码")
    @PostMapping("/register/validate")
    JsonResponse<CommonVO<Boolean>> registerValidate (@RequestBody SMSValidateReq req);

    @ApiOperation("重置密码-发送短信")
    @PostMapping("/reset/send/mobile")
    JsonResponse<CommonVO<Boolean>> resetSend (@RequestBody SMSSendReq req);

    @ApiOperation ("重置密码-校验短信验证码")
    @PostMapping("/reset/validate")
    JsonResponse<CommonVO<Boolean>> resetValidate (@RequestBody SMSValidateReq req);

    @ApiOperation("通用-发送短信")
    @PostMapping("/common/send/mobile")
    JsonResponse<CommonVO<Boolean>> commonSend (@RequestBody SMSSendReq req);

    @ApiOperation ("通用-校验短信验证码")
    @PostMapping("/common/validate")
    JsonResponse<CommonVO<Boolean>> commonValidate (@RequestBody SMSValidateReq req);
}
