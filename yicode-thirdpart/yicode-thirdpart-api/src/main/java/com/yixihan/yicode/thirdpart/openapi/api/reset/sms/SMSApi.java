package com.yixihan.yicode.thirdpart.openapi.api.reset.sms;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SendSMSDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yixihan
 * @date 2022-10-27-15:43
 */
@Api(tags = "短信模块")
@RequestMapping("/sms/")
public interface SMSApi {

    @ApiOperation ("测试发送短信")
    @PostMapping("/testsend")
    ApiResult<String> testSend (@RequestBody SendSMSDtoReq dtoReq);

}
