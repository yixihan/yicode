package com.yixihan.yicode.question.openapi.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 编程语言类型枚举类
 *
 * @author yixihan
 * @date 2023/1/13 13:52
 */
@AllArgsConstructor
@Getter
public enum CodeTypeEnums {
    
    /**
     * Java
     */
    JAVA ("JAVA", "Java"),
    
    /**
     * JavaScript
     */
    JS ("JS", "JavaScript"),
    
    /**
     * Golang
     */
    GO ("GO", "go"),
    
    /**
     * Python
     */
    PY ("PY ", "py"),
    
    /**
     * C++
     */
    CPP ("CPP", "cpp"),
    
    /**
     * C
     */
    C ("C", "c");
    
    
    
    /**
     * 语言类型
     */
    private final String value;
    
    /**
     * 语言后缀
     */
    private final String type;
}
