package com.yixihan.yicode.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时间维度枚举类
 *
 * @author yixihan
 * @date 2023/3/6 9:44
 */
@Getter
@AllArgsConstructor
public enum DateDimensionEnums {
    
    WEEK ("WEEK"),
    
    MONTH ("MONTH"),
    
    YEAR ("YEAR");
    
    
    private final String dimension;
}
