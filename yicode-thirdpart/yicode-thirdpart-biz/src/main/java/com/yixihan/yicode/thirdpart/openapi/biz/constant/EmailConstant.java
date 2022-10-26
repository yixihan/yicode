package com.yixihan.yicode.thirdpart.openapi.biz.constant;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮件发送
 *
 * @author yixihan
 * @date 2022-10-26-11:11
 */
@Getter
@Component
public class EmailConstant {

    @Value("${yicode.email.username}")
    private String sendEmail;

    public static final String LOGIN_FORMAT = "email::login::%s";

    public static final String REGISTER_FORMAT = "email::register::%s";

    public static final String UPDATE_PASSWORD_FORMAT = "email::update-password::%s";

}
