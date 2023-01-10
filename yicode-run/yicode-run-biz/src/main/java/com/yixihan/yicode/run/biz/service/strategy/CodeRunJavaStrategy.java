package com.yixihan.yicode.run.biz.service.strategy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import com.yixihan.yicode.run.biz.service.CodeRunExtractService;
import com.yixihan.yicode.run.biz.service.CodeRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * 代码运行 Java
 *
 * @author yixihan
 * @date 2023/1/8 19:54
 */
@Slf4j
@Service("CodeRunJavaService")
public class CodeRunJavaStrategy implements CodeRunExtractService {
    
    @Resource
    private CodeRunService codeRunService;
    
    @Override
    public File createFile(String code) {
        return codeRunService.createFile (code, "Main.java");
    }
    
    @Override
    public CodeRunDtoResult run(CodeRunDtoReq req) throws Exception {
        // 创建源代码文件
        File file = createFile (req.getCode ());
    
        // 获取源代码目录
        String path = file.getParent ();
    
        // 编译
        String[] compileCommand = new String[]{"/bin/bash", "-c", "cd " + path + " && javac -encoding utf-8 Main.java"};
        String compileOutput = codeRunService.compile (compileCommand);
        // 判断是否编译成功
        if (StrUtil.isNotBlank (compileOutput)) {
            CodeRunDtoResult dtoResult = new CodeRunDtoResult ();
            dtoResult.setCompile (Boolean.FALSE);
            dtoResult.setAnsList (CollUtil.newArrayList (compileOutput));
            return dtoResult;
        }
    
        // 运行
        String[] runCommand = new String[]{"/bin/bash", "-c", "cd " + path + " && java Main"};
        return codeRunService.run (req, runCommand);
    }
}
