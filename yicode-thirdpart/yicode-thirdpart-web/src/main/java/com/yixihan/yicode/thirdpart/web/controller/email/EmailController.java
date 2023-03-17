package com.yixihan.yicode.thirdpart.web.controller.email;

import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.rest.email.EmailApi;
import com.yixihan.yicode.thirdpart.biz.service.email.EmailSendService;
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
    private EmailSendService service;

    @Override
    public void send(EmailSendDtoReq req) {
        service.send (req);
    }

    @Override
    public void validate(EmailValidateDtoReq dtoReq) {
        service.validate (dtoReq);
    }

}
