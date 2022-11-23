package com.yixihan.yicode.thirdpart.api.enums.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证码类型枚举类
 *
 * @author yixihan
 * @date 2022/11/22 9:13
 */
@Getter
@AllArgsConstructor
public enum CodeTypeEnums {

    /**
     * 注册
     */
    REGISTER("REGISTER", "注册"),

    /**
     * 登录
     */
    LOGIN("LOGIN", "登录"),

    /**
     * 重置密码
     */
    RESET_PASSWORD("RESET_PASSWORD", "重置密码"),

    /**
     * 通用
     */
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
