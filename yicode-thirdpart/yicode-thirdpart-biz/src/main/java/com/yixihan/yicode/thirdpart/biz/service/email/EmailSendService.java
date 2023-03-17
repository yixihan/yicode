package com.yixihan.yicode.thirdpart.biz.service.email;

import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;

/**
 * 邮件服务类
 *
 * @author yixihan
 * @date 2022-10-26-11:02
 */
public interface EmailSendService {

    /**
     * 邮件发送
     *
     * @param dtoReq 请求参数
     */
    void send(EmailSendDtoReq dtoReq);

    /**
     * 邮件验证码验证
     *
     * @param dtoReq 请求参数
     */
    void validate(EmailValidateDtoReq dtoReq);
}
