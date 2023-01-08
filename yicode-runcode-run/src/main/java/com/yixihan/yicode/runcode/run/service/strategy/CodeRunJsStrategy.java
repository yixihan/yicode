package com.yixihan.yicode.runcode.run.service.strategy;

import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.runcode.run.dto.response.CodeRunDtoResult;
import com.yixihan.yicode.runcode.run.service.CodeRunExtractService;
import com.yixihan.yicode.runcode.run.service.CodeRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * 代码运行 Js
 *
 * @author yixihan
 * @date 2023/1/8 19:54
 */
@Slf4j
@Service("CodeRunJsService")
public class CodeRunJsStrategy implements CodeRunExtractService {
    
    @Resource
    private CodeRunService codeRunService;
    
    @Override
    public File createFile(String code, String type) {
        return null;
    }
    
    @Override
    public CodeRunDtoResult run(CodeRunDtoReq req) throws Exception {
        return null;
    }
    
    @Override
    public String compile(File file) throws Exception {
        return null;
    }
}
