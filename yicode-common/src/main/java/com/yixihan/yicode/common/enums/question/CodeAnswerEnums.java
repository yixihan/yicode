package com.yixihan.yicode.common.enums.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代码运行结果枚举类
 *
 * @author yixihan
 * @date 2023/1/13 13:54
 */
@Getter
@AllArgsConstructor
public enum CodeAnswerEnums {
    
    /**
     * 正确
     */
    AC ("AC",  "正确", "Accepted"),
    
    /**
     * 编译失败
     */
    CE ("CE", "编译失败", "Compile Error"),
    
    /**
     * 答案错误
     */
    WA ("WA", "答案错误", "Wrong Answer"),
    
    /**
     * 输出超限
     */
    OL ("OL", "输出超限", "Output Limit" ),
    
    /**
     * 格式错误
     */
    PE ("PE", "格式错误", "Presentation Error"),
    
    /**
     * 时间超限
     */
    TLE ("TLE", "时间超限", "Time Limited Exceed"),
    
    /**
     * 运行错误
     */
    RE ("RE", "运行错误", "Runtime Error"),
    
    /**
     * 系统内部错误
     */
    SE ("OE", "系统内部错误", "System Error");
    
    /**
     * 答案
     */
    private final String answer;
    
    /**
     * 中文名
     */
    private final String chineseName;
    
    /**
     * 描述
     */
    private final String description;
}
