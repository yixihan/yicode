package com.yixihan.yicode.thirdpart.openapi.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description
 *
 * @author yixihan
 * @date 2022/11/22 9:13
 */
@Getter
@AllArgsConstructor
public enum CodeTypeEnums {

    REGISTER("REGISTER", "注册"),

    LOGIN("LOGIN", "登录"),

    RESET_PASSWORD("RESET_PASSWORD", "重置密码"),

    COMMON("COMMON", "通用");

    /**
     * type
     */
    private final String type;

    /**
     * 描述
     */
    private final String description;

}
