package com.yixihan.yicode.run.biz.service;

import java.util.HashMap;
import java.util.Map;

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
    
    private static final Map<String,String> STRATEGY_MAP = new HashMap<> ();
    
    
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
}
