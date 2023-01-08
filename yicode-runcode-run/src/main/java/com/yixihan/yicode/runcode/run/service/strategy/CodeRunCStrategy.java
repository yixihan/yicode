package com.yixihan.yicode.runcode.run.service.strategy;

import cn.hutool.core.io.FileUtil;
import com.yixihan.yicode.common.util.SnowFlake;
import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.runcode.run.service.CodeRunConfig;
import com.yixihan.yicode.runcode.run.service.CodeRunExtractService;
import com.yixihan.yicode.runcode.run.service.CodeRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码运行 c语言
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
    public File createFile(String code, String type) {
        // 生成源代码目录
        File path = new File (codeRunService.getPath () + "/" + SnowFlake.nextId ());
        FileUtil.mkdir (path);
        // 生成源代码文件
        File file = new File (path + "/main.c");
        // 写入代码
        codeRunService.writeCode (code, file);
    
        return file;
    }
    
    
    @Override
    public List<String> run(CodeRunDtoReq req) throws Exception {
        // 创建源代码文件
        File file = createFile (req.getCode (), req.getCodeType ());
        // 判断是否是需要编译的语言
        if (CodeRunConfig.judgeCodeCompile (req.getCodeType ())) {
            compile (file);
        }
        // 运行代码
        File path = FileUtil.getParent (file, 1);
        String command = "/bin/bash -c cd " + path + " && ./main";
        log.info ("run command : {}", command);
        List<String> ansList = new ArrayList<> ();
        
        long startTime = System.currentTimeMillis ();
        for (List<String> params : req.getParamList ()) {
            Process process = Runtime.getRuntime ().exec (command);
            ansList.add (codeRunService.runProcess (process, params));
            while (process.isAlive ()){
                Thread.sleep (10);
                log.info ("等待程序执行...");
            }
            process.destroy ();
        }
        long endTime = System.currentTimeMillis ();
        log.info ("time used : {}", (endTime - startTime));
        
        return ansList;
    }
    
    @Override
    public void compile(File file) throws Exception {
        File path = FileUtil.getParent (file, 1);
        String fileName = FileUtil.getName (file);
        String command = "/bin/bash -c cd " + path + " && gcc " + fileName + " -o main";
        log.info ("compile command : {}", command);
        Process process = Runtime.getRuntime ().exec (command);
        process.destroy ();
    }
}
