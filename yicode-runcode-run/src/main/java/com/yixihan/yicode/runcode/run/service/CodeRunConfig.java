package com.yixihan.yicode.runcode.run.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 代码运行 配置类
 *
 * @author yixihan
 * @date 2023/1/8 11:21
 */
public class CodeRunConfig {
    
    //================================代码类型================================
    
    /**
     * Java
     */
    public static final String CODE_TYPE_JAVA = "JAVA";
    
    /**
     * c
     */
    public static final String CODE_TYPE_C = "C";
    
    /**
     * c++
     */
    public static final String CODE_TYPE_CPP = "CPP";
    
    /**
     * JavaScript
     */
    public static final String CODE_TYPE_JS = "JS";
    
    /**
     * go
     */
    public static final String CODE_TYPE_GO = "GO";
    
    /**
     * python
     */
    public static final String CODE_TYPE_PY = "PY";
    
    
    //================================需要编译的代码类型================================
    
    /**
     * Java
     */
    public static final String COMPILE_TYPE_JAVA = "JAVA";
    
    /**
     * c
     */
    public static final String COMPILE_TYPE_C = "C";
    
    /**
     * c++
     */
    public static final String COMPILE_TYPE_CPP = "CPP";
    
    private static final Map<String,String> STRATEGY_MAP = new HashMap<> ();
    
    private static final Set<String> COMPILE_SET = new HashSet<> ();
    
    /**
     * 添加服务
     */
    public static void putServiceName (String type, String serviceName) {
        STRATEGY_MAP.put (type, serviceName);
    }
    
    /**
     * 获取服务名
     */
    public static String getServiceName (String type) {
        return STRATEGY_MAP.get (type);
    }
    
    // 添加需要编译的代码类型服务
    static {
        COMPILE_SET.add (COMPILE_TYPE_JAVA);
        COMPILE_SET.add (COMPILE_TYPE_C);
        COMPILE_SET.add (COMPILE_TYPE_CPP);
    }
    
    /**
     * 判断该语言是否需要编译
     */
    public static Boolean judgeCodeCompile (String type) {
        return COMPILE_SET.contains (type);
    }
    
}
