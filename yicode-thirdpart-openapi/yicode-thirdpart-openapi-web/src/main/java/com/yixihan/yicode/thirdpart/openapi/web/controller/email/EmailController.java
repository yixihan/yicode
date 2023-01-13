package com.yixihan.yicode.thirdpart.openapi.web.controller.email;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
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
    private EmailService emailService;
    
    
    @Override
    @SentinelResource(value = "sendEmail",
            blockHandler = "blockHandlerEmail",
            blockHandlerClass = EmailBlockHandler.class
    )
    public JsonResponse<CommonVO<Boolean>> loginSend(EmailSendReq req) {
        return JsonResponse.ok (emailService.loginSend (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> loginValidate(EmailValidateReq req) {
        return JsonResponse.ok (emailService.loginValidate (req));
    }

    @Override
    @SentinelResource(value = "sendEmail",
            blockHandler = "blockHandlerEmail",
            blockHandlerClass = EmailBlockHandler.class
    )
    public JsonResponse<CommonVO<Boolean>> registerSend(EmailSendReq req) {
        return JsonResponse.ok (emailService.registerSend (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> registerValidate(EmailValidateReq req) {
        return JsonResponse.ok (emailService.registerValidate (req));
    }

    @Override
    @SentinelResource(value = "sendEmail",
            blockHandler = "blockHandlerEmail",
            blockHandlerClass = EmailBlockHandler.class
    )
    public JsonResponse<CommonVO<Boolean>> resetSend(EmailSendReq req) {
        return JsonResponse.ok (emailService.resetSend (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> resetValidate(EmailValidateReq req) {
        return JsonResponse.ok (emailService.resetValidate (req));
    }

    @Override
    @SentinelResource(value = "sendEmail",
            blockHandler = "blockHandlerEmail",
            blockHandlerClass = EmailBlockHandler.class
    )
    public JsonResponse<CommonVO<Boolean>> commonSend(EmailSendReq req) {
        return JsonResponse.ok (emailService.commonSend (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> commonValidate(EmailValidateReq req) {
        return JsonResponse.ok (emailService.commonValidate (req));
    }
}
