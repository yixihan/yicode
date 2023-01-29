package com.yixihan.yicode.question.api.enums;

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
    
    AC ("AC",  "正确", "Accepted"),
    
    CE ("CE", "编译失败", "Compile Error"),
    
    WA ("WA", "答案错误", "Wrong Answer"),
    
    OL ("OL", "输出超限", "Output Limit" ),
    
    PE ("PE", "格式错误", "Presentation Error"),
    
    TLE ("TLE", "时间超限", "Time Limited Exceed"),
    
    RE ("RE", "运行错误", "Runtime Error"),
    
    SE ("OE", "系统内部错误", "System Error");
    
    
    private final String answer;
    
    private final String chineseName;
    
    private final String description;
}
