package com.yixihan.yicode.thirdpart.openapi.biz.service.sms;

import com.yixihan.yicode.common.enums.SMSTypeEnums;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SendSMSDtoReq;

/**
 * 短信发送服务类
 *
 * @author yixihan
 * @date 2022-10-27-15:48
 */
public interface SMSService {

    /**
     * 发送手机短信
     * <p>
     * 短信发送类型枚举类 : {@link SMSTypeEnums}
     *
     * @param dtoReq
     * @return
     */
    String send(SendSMSDtoReq dtoReq);
}
