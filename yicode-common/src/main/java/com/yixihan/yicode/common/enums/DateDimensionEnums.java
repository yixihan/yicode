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
    
    /**
     * 周
     */
    WEEK ("WEEK"),
    
    /**
     * 月
     */
    MONTH ("MONTH"),
    
    /**
     * 年
     */
    YEAR ("YEAR");
    
    /**
     * 时间统计维度
     */
    private final String dimension;
}
