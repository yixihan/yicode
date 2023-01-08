package com.yixihan.yicode.runcode.run.service;

import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.runcode.run.dto.response.CodeRunDtoResult;

import java.io.File;

/**
 * 代码运行 策略服务类
 *
 * @author yixihan
 * @date 2023/1/8 11:20
 */
public interface CodeRunExtractService {
    
    /**
     * 生成代码源文件
     *
     * @param code 代码
     * @param type 代码类型 (JAVA, JS, C, CPP, GO, PY)
     * @return 代码源文件
     */
    File createFile(String code, String type);
    
    /**
     * 运行代码
     *
     * @param req 请求参数
     * @return 代码运行结果
     */
    CodeRunDtoResult run (CodeRunDtoReq req) throws Exception;
    
    /**
     * 编译代码
     *
     * @param file 代码源文件
     * @return 编译输出 (为空则表示编译成功)
     */
    String compile (File file) throws Exception;
}
