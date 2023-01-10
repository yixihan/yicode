package com.yixihan.yicode.run.biz.service.strategy;


import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import com.yixihan.yicode.run.biz.service.CodeRunExtractService;
import com.yixihan.yicode.run.biz.service.CodeRunService;
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
        
        // 运行
        String[] runCommand = new String[]{"/bin/bash", "-c", "cd " + path + " && python3 main.py"};
        return codeRunService.run (req, runCommand);
    }
    
}
