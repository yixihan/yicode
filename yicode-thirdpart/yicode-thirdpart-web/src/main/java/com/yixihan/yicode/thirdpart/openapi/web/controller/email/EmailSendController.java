package com.yixihan.yicode.thirdpart.openapi.web.controller.email;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SendEmailDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.reset.email.EmailSendApi;
import com.yixihan.yicode.thirdpart.openapi.biz.service.email.EmailSendService;
import com.yixihan.yicode.thirdpart.openapi.web.fallback.EmailFallback;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yixihan
 * @date 2022-10-24-20:04
 */
@RestController
public class EmailSendController implements EmailSendApi {

    @Resource
    private EmailSendService emailSendService;

    @Override
    @SentinelResource(value = "sendEmail", blockHandlerClass = EmailFallback.class, blockHandler = "emailBlockHandler")
    public ApiResult<String> sendEmail(SendEmailDtoReq req) {
        return ApiResult.create (emailSendService.sendEmail (req));
    }

}
