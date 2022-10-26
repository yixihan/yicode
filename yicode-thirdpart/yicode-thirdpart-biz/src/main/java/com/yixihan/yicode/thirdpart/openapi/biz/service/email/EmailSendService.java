package com.yixihan.yicode.thirdpart.openapi.biz.service.email;

import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SendEmailDtoReq;

/**
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
    String sendEmail (SendEmailDtoReq dtoReq);
}
