package com.yixihan.yicode.runcode.run.service;

import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.runcode.run.enums.CodeTypeEnums;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
     * @param type 代码类型 {@link CodeTypeEnums}
     * @return 代码源文件
     */
    File createFile(String code, String type);
    
    /**
     * 运行代码
     *
     * @param req 请求参数
     * @return 代码运行结果
     */
    List<String> run (CodeRunDtoReq req) throws IOException, InterruptedException, Exception;
    
    /**
     * 编译代码
     *
     * @param file 代码源文件
     */
    void compile (File file) throws IOException, InterruptedException, Exception;
}
