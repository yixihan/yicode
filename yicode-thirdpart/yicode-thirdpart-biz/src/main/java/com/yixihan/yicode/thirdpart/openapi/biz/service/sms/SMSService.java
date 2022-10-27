package com.yixihan.yicode.thirdpart.openapi.biz.service.sms;

import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SendSMSDtoReq;

/**
 * 短信发送服务类
 *
 * @author yixihan
 * @date 2022-10-27-15:48
 */
public interface SMSService {

    /**
     * 测试发送手机短信
     *
     * @param dtoReq
     * @return
     */
    String testSend(SendSMSDtoReq dtoReq);
}
