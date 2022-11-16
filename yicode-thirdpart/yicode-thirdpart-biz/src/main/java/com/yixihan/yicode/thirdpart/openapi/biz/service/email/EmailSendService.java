package com.yixihan.yicode.thirdpart.openapi.biz.service.email;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailValidateDtoReq;

/**
 * 邮件发送服务类
 *
 * @author yixihan
 * @date 2022-10-26-11:02
 */
public interface EmailSendService {

    /**
     * 邮件发送
     *
     * @param dtoReq
     * @return
     */
    CommonDtoResult<Boolean> sendEmail (EmailSendDtoReq dtoReq);

    /**
     * 邮件验证码验证
     *
     * @param dtoReq
     * @return
     */
    CommonDtoResult<Boolean> validate(EmailValidateDtoReq dtoReq);
}
