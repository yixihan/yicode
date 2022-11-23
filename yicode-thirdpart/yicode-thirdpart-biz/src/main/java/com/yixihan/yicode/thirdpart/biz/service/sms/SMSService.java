package com.yixihan.yicode.thirdpart.biz.service.sms;

import com.yixihan.yicode.thirdpart.api.enums.SMSTemplateEnums;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.api.dto.request.SMSValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.SMSSendDtoReq;

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
     * 短信发送类型枚举类 : {@link SMSTemplateEnums}
     *
     * @param dtoReq
     * @return
     */
    CommonDtoResult<Boolean> send(SMSSendDtoReq dtoReq);

    /**
     * 校验手机验证码
     * <p>
     * 短信发送类型枚举类 : {@link SMSTemplateEnums}
     * @param dtoReq
     * @return
     */
    CommonDtoResult<Boolean> validate(SMSValidateDtoReq dtoReq);
}
