package com.yixihan.yicode.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 邮件发送类型枚举类
 *
 * @author yixihan
 * @date 2022-10-28-9:53
 */
@AllArgsConstructor
@Getter
public enum EmailTypeEnums {

    /**
     * 登录模板
     */
    LOGIN(1L, "登录模板"),

    /**
     * 注册模板
     */
    REGISTER(2L, "注册模板"),

    /**
     * 更新密码模板
     */
    UPDATE_PASSWORD(3L, "更新密码模板"),

    /**
     * 通用模板
     */
    COMMON(4L, "通用模板")

    ;

    /**
     * 模板 id
     */
    private final Long id;

    /**
     * 描述
     */
    private final String description;
}
