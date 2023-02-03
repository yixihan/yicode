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
    LOGIN(10000000L, "登录模板"),

    /**
     * 注册模板
     */
    REGISTER(10000001L, "注册模板"),

    /**
     * 更新密码模板
     */
    RESET_PASSWORD(10000002L, "更新密码模板"),

    /**
     * 通用模板
     */
    COMMON(10000003L, "通用模板"),

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
