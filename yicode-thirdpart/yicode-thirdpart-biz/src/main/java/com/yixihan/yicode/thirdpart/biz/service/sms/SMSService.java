package com.yixihan.yicode.thirdpart.biz.service.sms;

import com.yixihan.yicode.common.enums.thirdpart.oss.SMSTemplateEnums;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;

/**
 * 短信服务类
 *
 * @author yixihan
 * @date 2022-10-27-15:48
 */
public interface SMSService {

    /**
     * 发送手机短信
     * <p>
     * 短信发送类型枚举类 : {@link SMSTemplateEnums}
     *
     * @param dtoReq 请求参数
     */
    void send(SMSSendDtoReq dtoReq);

    /**
     * 校验短信验证码
     * <p>
     * 短信发送类型枚举类 : {@link SMSTemplateEnums}
     *
     * @param dtoReq 请求参数
     */
    void validate(SMSValidateDtoReq dtoReq);
}
