package com.yixihan.yicode.user.openapi.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description
 *
 * @author yixihan
 * @date 2022/12/21 16:16
 */
@AllArgsConstructor
@Getter
public enum UserSexTypeEnums {
    
    /**
     * 保密
     */
    SECRECY("SECRECY", "保密"),
    
    /**
     * 男性
     */
    MAN ("MAN", "男性"),
    
    /**
     * 女性
     */
    WOMAN("WOMAN", "女性");
    
    /**
     * 性别
     */
    private final String type;
    
    /**
     * 描述
     */
    private final String description;
}
