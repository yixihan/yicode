package com.yixihan.yicode.user.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户注册方式枚举类
 *
 * @author yixihan
 * @date 2022/11/21 20:27
 */
@AllArgsConstructor
@Getter
public enum LoginTypeEnums {

    /**
     * 用户名+密码注册
     */
    USERNAME_PASSWORD("USERNAME_PASSWORD", "用户名密码"),

    /**
     * 手机号+验证码注册
     */
    MOBILE_CODE("MOBILE_CODE", "手机号"),

    /**
     * 邮箱+验证码注册
     */
    EMAIL_CODE("EMAIL_CODE", "邮箱");

    /**
     * 注册方式
     */
    private final String type;

    /**
     * 描述
     */
    private final String description;
}
