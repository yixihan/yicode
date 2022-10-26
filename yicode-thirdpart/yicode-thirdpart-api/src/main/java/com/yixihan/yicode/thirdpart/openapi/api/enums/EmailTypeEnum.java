package com.yixihan.yicode.thirdpart.openapi.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 邮件发送枚举类
 *
 * @author yixihan
 * @date 2022-10-26-10:51
 */
@Getter
@AllArgsConstructor
public enum EmailTypeEnum {

    /**
     * 登录
     */
    LOGIN_EMAIL (1, "登录"),

    /**
     * 注册
     */
    REGISTER_EMAIL (2, "注册"),

    /**
     * 修改密码
     */
    CHANGE_PASSWORD_EMAIL (3, "修改密码");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String description;
}
