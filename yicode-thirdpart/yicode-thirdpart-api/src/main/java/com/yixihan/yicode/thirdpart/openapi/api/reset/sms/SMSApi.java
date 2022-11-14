package com.yixihan.yicode.thirdpart.openapi.api.reset.sms;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SMSValidateDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SMSSendDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 短信发送 api
 *
 * @author yixihan
 * @date 2022-10-27-15:43
 */
@Api(tags = "短信模块")
@RequestMapping("/sms/")
public interface SMSApi {

    @ApiOperation ("发送短信")
    @PostMapping("/sendmobile")
    ApiResult<String> send (@RequestBody SMSSendDtoReq dtoReq);

    @ApiOperation ("校验验证码")
    @PostMapping("/validate")
    ApiResult<Boolean> validate (@RequestBody SMSValidateDtoReq dtoReq);

}
