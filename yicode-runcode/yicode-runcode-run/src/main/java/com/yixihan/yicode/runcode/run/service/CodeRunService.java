package com.yixihan.yicode.runcode.run.service;

import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 代码编译运行 服务类
 *
 * @author yixihan
 * @date 2023/1/3 14:18
 */
public interface CodeRunService {
    
    /**
     * 生成 Python 代码编译运行指令
     *
     * @param file 代码文件
     * @return {@link Process} 指令对象
     */
    Process getPyProcess(File file) throws IOException;
    
    /**
     * 生成 C 代码编译运行指令
     *
     * @param file 代码文件
     * @return {@link Process} 指令对象
     */
    Process getCProcess(File file) throws IOException;
    
    /**
     * 生成 Java 代码编译运行指令
     *
     * @param file 代码文件
     * @return {@link Process} 指令对象
     */
    Process getJavaProcess(File file) throws IOException;
    
    /**
     * 生成 C++ 代码编译运行指令
     *
     * @param file 代码文件
     * @return {@link Process} 指令对象
     */
    Process getCppProcess(File file) throws IOException;
    
    /**
     * 生成 JavaScript 代码编译运行指令
     *
     * @param file 代码文件
     * @return {@link Process} 指令对象
     */
    Process getJsProcess(File file) throws IOException;
    
    /**
     * 生成 Golang 代码编译运行指令
     *
     * @param file 代码文件
     * @return {@link Process} 指令对象
     */
    Process getGoProcess(File file) throws IOException;
    
    /**
     * 编译运行代码, 获取运行结果
     *
     * @param process 指令对象
     * @param params 传参
     * @return {@link Process} 指令对象
     */
    String runProcess(Process process, List<String> params) throws IOException;
    
    /**
     * 执行代码
     *
     * @param dtoReq 请求参数
     * @return 执行结果
     */
    String runCode(CodeRunDtoReq dtoReq);
}
