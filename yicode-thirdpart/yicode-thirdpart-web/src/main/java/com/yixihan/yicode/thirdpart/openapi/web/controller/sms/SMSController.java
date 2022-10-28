package com.yixihan.yicode.thirdpart.openapi.web.controller.sms;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SendSMSDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.reset.sms.SMSApi;
import com.yixihan.yicode.thirdpart.openapi.biz.service.sms.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yixihan
 * @date 2022-10-27-15:47
 */
@Slf4j
@RestController
public class SMSController implements SMSApi {

    @Resource
    public SMSService smsService;


    @Override
    public ApiResult<String> send(SendSMSDtoReq dtoReq) {
        return ApiResult.create (smsService.send (dtoReq));
    }
}
