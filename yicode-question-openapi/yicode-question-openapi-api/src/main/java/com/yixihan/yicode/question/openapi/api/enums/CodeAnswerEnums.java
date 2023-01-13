package com.yixihan.yicode.question.openapi.api.enums;

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
    
    AC ("AC", "Accepted"),
    
    CE ("CE", "Compile Error"),
    
    WA ("WA", "Wrong Answer"),
    
    OL ("OL", "Output Limit"),
    
    PE ("PE", "Presentation Error"),
    
    TLE ("TLE", "Time Limited Exceed"),
    
    RE ("RE", "Runtime Error");
    
    
    private final String answer;
    
    private final String description;
}
