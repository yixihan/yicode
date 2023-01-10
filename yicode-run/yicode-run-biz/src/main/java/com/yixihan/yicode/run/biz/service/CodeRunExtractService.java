package com.yixihan.yicode.run.biz.service;


import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;

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
     * @return 代码源文件
     */
    File createFile(String code);
    
    /**
     * 运行代码
     *
     * @param req 请求参数
     * @return 代码运行结果
     */
    CodeRunDtoResult run(CodeRunDtoReq req) throws Exception;
}
