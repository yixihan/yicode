package com.yixihan.yicode.common.enums.thirdpart.email;

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
    LOGIN("LOGIN", "登录模板"),

    /**
     * 注册模板
     */
    REGISTER("REGISTER", "注册模板"),

    /**
     * 更新密码模板
     */
    RESET_PASSWORD("RESET_PASSWORD", "更新密码模板"),

    /**
     * 通用模板
     */
    COMMON("COMMON", "通用模板")

    ;

    /**
     * 模板 id
     */
    private final String name;

    /**
     * 描述
     */
    private final String description;
}
