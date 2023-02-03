package com.yixihan.yicode.common.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 收藏夹类型枚举类
 *
 * @author yixihan
 * @date 2022/12/21 14:38
 */
@AllArgsConstructor
@Getter
public enum FavoriteTypeEnums {
    
    /**
     * 问题
     */
    QUESTION ("QUESTION", "问题"),
    
    /**
     * 题解
     */
    NOTE("NOTE", "题解");
    
    /**
     * 类型
     */
    private final String type;
    
    /**
     * 描述
     */
    private final String description;
    
    /**
     * 检测 type 是否属于枚举类
     *
     * @param type 待检测 type
     * @return 是 : true | 否 : false
     */
    public static Boolean contains (String type) {
        for (FavoriteTypeEnums enums : FavoriteTypeEnums.values ()) {
            if (enums.getType ().equals (type)) {
                return Boolean.TRUE;
            }
        }
        
        return Boolean.FALSE;
    }
}
