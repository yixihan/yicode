package com.yixihan.yicode.thirdpart.web.controller.sms;

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
    public SMSService service;


    @Override
    public void send(SMSSendDtoReq dtoReq) {
        service.send (dtoReq);
    }

    @Override
    public void validate(SMSValidateDtoReq dtoReq) {
        service.validate (dtoReq);
    }
}
