package com.yixihan.yicode.thirdpart.api.rest.sms;

import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 短信模块 api
 *
 * @author yixihan
 * @date 2022-10-27-15:43
 */
@Api(tags = "短信模块 api")
@RequestMapping("/sms")
public interface SMSApi {

    @ApiOperation ("发送短信")
    @PostMapping("/send")
    void send (@RequestBody SMSSendDtoReq dtoReq);

    @ApiOperation ("校验验证码")
    @PostMapping("/validate")
    void validate (@RequestBody SMSValidateDtoReq dtoReq);

}
