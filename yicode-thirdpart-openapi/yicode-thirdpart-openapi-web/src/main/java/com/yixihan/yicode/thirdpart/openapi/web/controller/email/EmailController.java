package com.yixihan.yicode.thirdpart.openapi.web.controller.email;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yixihan.yicode.thirdpart.open.api.rest.email.EmailOpenApi;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.service.email.EmailService;
import com.yixihan.yicode.thirdpart.openapi.web.fallback.EmailBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮件模块 前端控制器
 *
 * @author yixihan
 * @date 2022/11/23 14:44
 */
@Slf4j
@RestController
public class EmailController implements EmailOpenApi {
    
    @Resource
    private EmailService service;
    
    
    @Override
    @SentinelResource(value = "sendEmail",
            blockHandler = "blockHandlerEmail",
            blockHandlerClass = EmailBlockHandler.class
    )
    public void loginSend(EmailSendReq req) {
        service.loginSend (req);
    }

    @Override
    public void loginValidate(EmailValidateReq req) {
        service.loginValidate (req);
    }

    @Override
    @SentinelResource(value = "sendEmail",
            blockHandler = "blockHandlerEmail",
            blockHandlerClass = EmailBlockHandler.class
    )
    public void registerSend(EmailSendReq req) {
        service.registerSend (req);
    }

    @Override
    public void registerValidate(EmailValidateReq req) {
        service.registerValidate (req);
    }

    @Override
    @SentinelResource(value = "sendEmail",
            blockHandler = "blockHandlerEmail",
            blockHandlerClass = EmailBlockHandler.class
    )
    public void resetSend(EmailSendReq req) {
        service.resetSend (req);
    }

    @Override
    public void resetValidate(EmailValidateReq req) {
        service.resetValidate (req);
    }

    @Override
    @SentinelResource(value = "sendEmail",
            blockHandler = "blockHandlerEmail",
            blockHandlerClass = EmailBlockHandler.class
    )
    public void commonSend(EmailSendReq req) {
        service.commonSend (req);
    }

    @Override
    public void commonValidate(EmailValidateReq req) {
        service.commonValidate (req);
    }
}
