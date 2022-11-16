package com.yixihan.yicode.thirdpart.openapi.web.controller.email;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.reset.email.EmailSendApi;
import com.yixihan.yicode.thirdpart.openapi.biz.service.email.EmailSendService;
import com.yixihan.yicode.thirdpart.openapi.web.fallback.EmailFallback;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮箱 前端控制器
 *
 * @author yixihan
 * @date 2022-10-24-20:04
 */
@RestController
public class EmailSendController implements EmailSendApi {

    @Resource
    private EmailSendService emailSendService;

    @Override
    @SentinelResource(value = "sendEmail", blockHandlerClass = EmailFallback.class, blockHandler = "emailBlockHandler")
    public ApiResult<CommonDtoResult<Boolean>> sendEmail(EmailSendDtoReq req) {
        return ApiResult.create (emailSendService.sendEmail (req));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> validate(EmailValidateDtoReq dtoReq) {
        return ApiResult.create (emailSendService.validate (dtoReq));
    }

}
