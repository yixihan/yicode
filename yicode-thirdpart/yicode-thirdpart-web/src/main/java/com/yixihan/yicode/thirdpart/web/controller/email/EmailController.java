package com.yixihan.yicode.thirdpart.web.controller.email;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.biz.service.email.EmailSendService;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.reset.email.EmailApi;
import com.yixihan.yicode.thirdpart.web.fallback.email.EmailFallback;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮箱 前端控制器
 *
 * @author yixihan
 * @date 2022-10-24-20:04
 */
@RestController
public class EmailController implements EmailApi {

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
