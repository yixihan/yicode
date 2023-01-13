package com.yixihan.yicode.thirdpart.web.controller.sms;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.rest.sms.SMSApi;
import com.yixihan.yicode.thirdpart.biz.service.sms.SMSService;
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
    public ApiResult<CommonDtoResult<Boolean>> send(SMSSendDtoReq dtoReq) {
        return ApiResult.create (smsService.send (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> validate(SMSValidateDtoReq dtoReq) {
        return ApiResult.create (smsService.validate (dtoReq));
    }
}
