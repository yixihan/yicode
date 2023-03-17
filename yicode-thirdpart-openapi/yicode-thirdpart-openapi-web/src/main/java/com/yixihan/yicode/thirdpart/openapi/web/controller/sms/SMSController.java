package com.yixihan.yicode.thirdpart.openapi.web.controller.sms;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yixihan.yicode.thirdpart.open.api.rest.sms.SMSOpenApi;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.service.sms.SMSService;
import com.yixihan.yicode.thirdpart.openapi.web.fallback.SMSBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短信模块 前端控制器
 *
 * @author yixihan
 * @date 2022/11/23 14:44
 */
@Slf4j
@RestController
public class SMSController implements SMSOpenApi {
    
    @Resource
    private SMSService service;
    
    @Override
    @SentinelResource(value = "sendSMS",
            blockHandler = "blockHandlerSMS",
            blockHandlerClass = SMSBlockHandler.class
    )
    public void loginSend(SMSSendReq req) {
        service.loginSend (req);
    }

    @Override
    public void loginValidate(SMSValidateReq req) {
        service.loginValidate (req);
    }

    @Override
    @SentinelResource(value = "sendSMS",
            blockHandler = "blockHandlerSMS",
            blockHandlerClass = SMSBlockHandler.class
    )
    public void registerSend(SMSSendReq req) {
        service.registerSend (req);
    }

    @Override
    public void registerValidate(SMSValidateReq req) {
        service.registerValidate (req);
    }

    @Override
    @SentinelResource(value = "sendSMS",
            blockHandler = "blockHandlerSMS",
            blockHandlerClass = SMSBlockHandler.class
    )
    public void resetSend(SMSSendReq req) {
        service.resetSend (req);
    }

    @Override
    public void resetValidate(SMSValidateReq req) {
        service.resetValidate (req);
    }

    @Override
    @SentinelResource(value = "sendSMS",
            blockHandler = "blockHandlerSMS",
            blockHandlerClass = SMSBlockHandler.class
    )
    public void commonSend(SMSSendReq req) {
        service.commonSend (req);
    }

    @Override
    public void commonValidate(SMSValidateReq req) {
        service.commonValidate (req);
    }
}
