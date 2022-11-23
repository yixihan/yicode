package com.yixihan.yicode.thirdpart.api.reset.sms;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.api.dto.request.SMSValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.SMSSendDtoReq;
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
    ApiResult<CommonDtoResult<Boolean>> send (@RequestBody SMSSendDtoReq dtoReq);

    @ApiOperation ("校验验证码")
    @PostMapping("/validate")
    ApiResult<CommonDtoResult<Boolean>> validate (@RequestBody SMSValidateDtoReq dtoReq);

}
