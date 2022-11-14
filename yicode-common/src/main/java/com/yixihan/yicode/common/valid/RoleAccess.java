package com.yixihan.yicode.common.valid;

import com.yixihan.yicode.common.enums.RoleEnum;

import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义注解, 配置接口可访问角色信息
 *
 * @author yixihan
 * @date 2022-10-23-21:55
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleAccess {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // 角色信息
    RoleEnum[] value() default {};
}
