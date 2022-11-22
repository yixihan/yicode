package com.yixihan.yicode.thirdpart.openapi.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证码方式枚举类
 *
 * @author yixihan
 * @date 2022/11/22 10:53
 */
@Getter
@AllArgsConstructor
public enum VerificationCodeEnums {

    /**
     * 手机号验证码
     */
    SMS("SMS", "手机号验证码"),

    /**
     * 邮箱验证码
     */
    EMAIL("EMAIL", "邮箱验证码"),

    /**
     * 图形验证码
     */
    PHOTO("PHOTO", "图形验证码");


    /**
     * 方式
     */
    private final String method;

    /**
     * 描述
     */
    private final String description;
}
