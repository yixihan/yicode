package com.yixihan.yicode.thirdpart.api.prop.sms;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * SMS 配置参数
 *
 * @author yixihan
 * @date 2022-10-26-11:08
 */
@Getter
@Component
public class SMSProp {

    @Value ("${yicode.txcloud.phone.secretId}")
    private String secretId;

    @Value ("${yicode.txcloud.phone.secretKey}")
    private String secretKey;

    @Value ("${yicode.txcloud.phone.smsSdkAppId}")
    private String smsSdkAppId;

    @Value ("${yicode.txcloud.phone.signName}")
    private String signName;

    // **** redis key **** //

    @Value ("${yicode.thirdpart.sms.login-key}")
    private String loginKey;

    @Value ("${yicode.thirdpart.sms.register-key}")
    private String registerKey;

    @Value ("${yicode.thirdpart.sms.update-password-key}")
    private String updatePasswordKey;

    @Value ("${yicode.thirdpart.sms.common-key}")
    private String commonKey;
}
