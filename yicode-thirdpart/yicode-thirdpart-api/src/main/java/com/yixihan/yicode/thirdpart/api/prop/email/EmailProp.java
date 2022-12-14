package com.yixihan.yicode.thirdpart.api.prop.email;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮件 配置参数
 *
 * @author yixihan
 * @date 2022-10-26-11:11
 */
@Getter
@Component
public class EmailProp {

    @Value("${yicode.email.username}")
    private String sendEmail;

    @Value (("${yicode.name}"))
    private String title;

    // **** redis key **** //

    @Value ("${yicode.thirdpart.email.login-key}")
    private String loginKey;

    @Value ("${yicode.thirdpart.email.register-key}")
    private String registerKey;

    @Value ("${yicode.thirdpart.email.update-password-key}")
    private String updatePasswordKey;

    @Value ("${yicode.thirdpart.email.common-key}")
    private String commonKey;

}
