package com.yixihan.yicode.question.openapi.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 问题难度类型枚举类
 *
 * @author yixihan
 * @date 2023/1/16 9:35
 */
@AllArgsConstructor
@Getter
public enum QuestionDifficultyTypeEnums {
    
    /**
     * 简单
     */
    EASY ("EASY", "简单"),
    
    /**
     * 中等
     */
    MEDIUM ("MEDIUM", "中等"),
    
    /**
     * 困难
     */
    HARD ("HARD", "困难");
    
    
    /**
     * 难度
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
        for (AnswerTypeEnums enums : AnswerTypeEnums.values ()) {
            if (enums.getType ().equals (type)) {
                return Boolean.TRUE;
            }
        }
        
        return Boolean.FALSE;
    }
}
