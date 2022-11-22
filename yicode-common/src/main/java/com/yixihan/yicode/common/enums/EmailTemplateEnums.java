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
public enum EmailTemplateEnums {

    /**
     * 登录模板
     */
    LOGIN(10000000L, "登录模板"),

    /**
     * 注册模板
     */
    REGISTER(10000001L, "注册模板"),

    /**
     * 更新密码模板
     */
    UPDATE_PASSWORD(10000002L, "更新密码模板"),

    /**
     * 通用模板
     */
    COMMON(10000003L, "通用模板")

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
