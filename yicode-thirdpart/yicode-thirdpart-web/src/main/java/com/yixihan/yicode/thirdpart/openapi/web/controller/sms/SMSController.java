package com.yixihan.yicode.thirdpart.openapi.web.controller.sms;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SMSValidateDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SMSSendDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.reset.sms.SMSApi;
import com.yixihan.yicode.thirdpart.openapi.biz.service.sms.SMSService;
import com.yixihan.yicode.thirdpart.openapi.web.fallback.SMSFallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短信发送 前端控制器
 *
 * @author yixihan
 * @date 2022-10-27-15:47
 */
@Slf4j
@RestController
public class SMSController implements SMSApi {

    @Resource
    public SMSService smsService;


    @Override
    @SentinelResource(value = "sendSMS", blockHandlerClass = SMSFallback.class, blockHandler = "emailBlockHandler")
    public ApiResult<String> send(SMSSendDtoReq dtoReq) {
        return ApiResult.create (smsService.send (dtoReq));
    }

    @Override
    public ApiResult<Boolean> validate(SMSValidateDtoReq dtoReq) {
        return ApiResult.create (smsService.validate (dtoReq));
    }
}
