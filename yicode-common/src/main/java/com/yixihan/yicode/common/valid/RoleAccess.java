package com.yixihan.yicode.common.valid;

import com.yixihan.yicode.common.enums.user.RoleEnums;

import java.lang.annotation.*;

/**
 * 自定义注解, 配置接口可访问角色信息
 *
 * @author yixihan
 * @date 2022-10-23-21:55
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleAccess {
    
    RoleEnums[] value() default {};
}
