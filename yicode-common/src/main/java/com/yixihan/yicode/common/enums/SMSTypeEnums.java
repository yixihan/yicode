package com.yixihan.yicode.common.enums;

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
public enum SMSTypeEnums {

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
    COMMON(4L, "通用模板"),

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
