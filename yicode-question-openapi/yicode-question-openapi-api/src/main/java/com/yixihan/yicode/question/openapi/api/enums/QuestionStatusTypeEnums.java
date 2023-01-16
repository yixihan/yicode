package com.yixihan.yicode.question.openapi.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 问题状态类型枚举类
 *
 * @author yixihan
 * @date 2023/1/16 12:57
 */
@AllArgsConstructor
@Getter
public enum QuestionStatusTypeEnums {
    
    /**
     * 未开始
     */
    UN_DO ("UN_DO", "未开始"),
    
    /**
     * 未通过
     */
    UN_ACCESS ("UN_ACCESS", "未通过"),
    
    /**
     * 已通过
     */
    ACCESS ("ACCESS", "已通过");
    
    /**
     * 状态
     */
    private final String status;
    
    /**
     * 描述
     */
    private final String description;
}
