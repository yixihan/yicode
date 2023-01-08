package com.yixihan.yicode.runcode.judge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 编程语言类型枚举类
 *
 * @author yixihan
 * @date 2023/1/3 13:46
 */
@AllArgsConstructor
@Getter
public enum CodeTypeEnums {
    
    /**
     * Java
     */
    JAVA ("JAVA", "java"),
    
    /**
     * JavaScript
     */
    JS ("JS", "js"),
    
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
