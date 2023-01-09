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
 * 代码运行 Py
 *
 * @author yixihan
 * @date 2023/1/8 19:54
 */
@Slf4j
@Service("CodeRunPyService")
public class CodeRunPyStrategy implements CodeRunExtractService {
    
    @Resource
    private CodeRunService codeRunService;
    
    @Override
    public File createFile(String code) {
        return codeRunService.createFile (code, "main.py");
    }
    
    @Override
    public CodeRunDtoResult run(CodeRunDtoReq req) throws Exception {
        // 创建源代码文件
        File file = createFile (req.getCode ());
    
        // 获取源代码目录
        String path = file.getParent ();
        String[] runCommand = new String[]{"/bin/bash", "-c", "cd " + path + " && py main.py"};
    
        return codeRunService.run (req, runCommand, null);
    }
    
}
