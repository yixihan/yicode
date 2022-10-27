package com.yixihan.yicode.thirdpart.openapi.biz.constant;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 短信发送
 *
 * @author yixihan
 * @date 2022-10-26-11:08
 */
@Getter
@Component
public class SMSConstant {

    @Value ("${yicode.txcloud.phone.secretId}")
    private String secretId;

    @Value ("${yicode.txcloud.phone.secretKey}")
    private String secretKey;

    @Value ("${yicode.txcloud.phone.smsSdkAppId}")
    private String smsSdkAppId;

    @Value ("${yicode.txcloud.phone.templateId}")
    private String templateId;

    @Value ("${yicode.txcloud.phone.signName}")
    private String signName;
}
