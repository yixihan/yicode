package com.yixihan.yicode.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色枚举类
 *
 * @author yixihan
 * @date 2022-10-23-13:01
 */
@Getter
@AllArgsConstructor
public enum RoleEnums {

    /**
     * 超级管理员
     */
    SUPER_ADMIN(1L, "超级管理员"),

    /**
     * 管理员
     */
    ADMIN(2L, "管理员"),

    /**
     * 用户
     */
    USER(3L, "用户");


    /**
     * 角色 id
     */
    private final Long roleId;

    /**
     * 角色名
     */
    private final String roleName;

}
