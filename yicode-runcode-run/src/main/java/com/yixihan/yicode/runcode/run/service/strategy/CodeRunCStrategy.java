package com.yixihan.yicode.runcode.run.service.strategy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.runcode.run.dto.response.CodeRunDtoResult;
import com.yixihan.yicode.runcode.run.service.CodeRunExtractService;
import com.yixihan.yicode.runcode.run.service.CodeRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * 代码运行 c
 *
 * @author yixihan
 * @date 2023/1/8 11:43
 */
@Slf4j
@Service("CodeRunCService")
public class CodeRunCStrategy implements CodeRunExtractService {
    
    @Resource
    private CodeRunService codeRunService;
    
    @Override
    public File createFile(String code) {
        return codeRunService.createFile (code, "main.c");
    }
    
    
    @Override
    public CodeRunDtoResult run(CodeRunDtoReq req) throws Exception {
        // 创建源代码文件
        File file = createFile (req.getCode ());
    
        // 获取源代码目录
        String path = file.getParent ();
        
        // 编译
        String[] compileCommand = new String[]{"/bin/bash", "-c", "cd " + path + " && gcc main.c -o main"};
        String compileOutput = codeRunService.compile (compileCommand);
        // 判断是否编译成功
        if (StrUtil.isNotBlank (compileOutput)) {
            CodeRunDtoResult dtoResult = new CodeRunDtoResult ();
            dtoResult.setCompile (Boolean.FALSE);
            dtoResult.setAnsList (CollUtil.newArrayList (compileOutput));
            return dtoResult;
        }
        
        // 运行
        String[] runCommand = new String[]{"/bin/bash", "-c", "cd " + path + " && ./main"};
        return codeRunService.run (req, runCommand);
    }
}
