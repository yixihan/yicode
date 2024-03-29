package com.yixihan.yicode.thirdpart.api.rest.email;

import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 邮件模块 api
 *
 * @author yixihan
 * @date 2022-10-24-20:07
 */
@Api(tags = "邮件模块 api")
@RequestMapping("/email")
public interface EmailApi {

    @ApiOperation ("/发送邮件")
    @PostMapping("/send")
    void send(@RequestBody EmailSendDtoReq req);

    @ApiOperation ("校验验证码")
    @PostMapping("/validate")
    void validate (@RequestBody EmailValidateDtoReq dtoReq);
}
