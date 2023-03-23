package com.yixihan.yicode.common.enums.thirdpart.oss;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 短信发送类型模板
 *
 * @author yixihan
 * @date 2022-10-28-10:04
 */
@AllArgsConstructor
@Getter
public enum SMSTemplateEnums {

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
    COMMON("COMMON", "通用模板"),

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
