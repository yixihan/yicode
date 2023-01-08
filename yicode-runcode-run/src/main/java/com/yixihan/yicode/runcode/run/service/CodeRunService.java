package com.yixihan.yicode.runcode.run.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.runcode.run.dto.response.CodeRunDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * 代码运行 服务类
 *
 * @author yixihan
 * @date 2023/1/8 11:27
 */
@Slf4j
@Service
public class CodeRunService {
    
    @PostConstruct
    public void init() {
        // c
        CodeRunConfig.putServiceName (CodeRunConfig.CODE_TYPE_C, "CodeRunCService");
        // c++
        CodeRunConfig.putServiceName (CodeRunConfig.CODE_TYPE_CPP, "CodeRunCppService");
        // Java
        CodeRunConfig.putServiceName (CodeRunConfig.CODE_TYPE_JAVA, "CodeRunJavaService");
        // JavaScript
        CodeRunConfig.putServiceName (CodeRunConfig.CODE_TYPE_JS, "CodeRunJsService");
        // go
        CodeRunConfig.putServiceName (CodeRunConfig.CODE_TYPE_GO, "CodeRunGoService");
        // python
        CodeRunConfig.putServiceName (CodeRunConfig.CODE_TYPE_PY, "CodeRunPyService");
    }
    
    /**
     * 代码运行策略选择器
     */
    public CodeRunDtoResult fetchCodeRunItem(CodeRunDtoReq req) {
        CodeRunDtoResult dtoResult;
        try {
            if (req == null) {
                return new CodeRunDtoResult ();
            }
    
            // 提取服务
            CodeRunExtractService service = SpringUtil.getBean (
                    CodeRunConfig.getServiceName (req.getCodeType ()),
                    CodeRunExtractService.class
            );
    
            // 运行代码
            dtoResult = service.run (req);
        } catch (Exception e) {
            log.error (e.getMessage ());
            return CodeRunDtoResult.error (e);
        }
        return dtoResult;
    }
    
    /**
     * 解码代码
     *
     * @param code 解码前的代码
     * @return 解码后的代码
     */
    public String decodeCode (String code) {
        return Base64.decodeStr (code);
    }
    
    /**
     * 获取代码存放目录
     *
     * @return 代码存放目录
     */
    public File getPath () {
        return new File ("/tmp/yicode" + DateUtil.format (new Date (), "yyyy-MM-dd-HH"));
    }
    
    /**
     * 将代码写入文件
     *
     * @param code 代码
     * @param file 要写入的文件
     */
    public void writeCode(String code, File file) {
        // 解码代码
        code = decodeCode (code);
        // 代码写入文件
        try (BufferedWriter bufferedWriter = new BufferedWriter (new FileWriter (file.getPath ()))) {
            bufferedWriter.write (code);
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }
    
    /**
     * 获取运行结果
     *
     * @param process 指令对象
     * @param params 传参
     * @return {@link Process} 指令对象
     */
    public String runCode(Process process, List<String> params) throws Exception {
        BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (process.getOutputStream ()));
        SequenceInputStream sis = new SequenceInputStream (process.getInputStream (), process.getErrorStream ());
        BufferedReader reader = new BufferedReader (new InputStreamReader (sis, "GBK"));
    
        // 传入形参
        for (String param : params) {
            writer.write (param);
            writer.newLine ();
        }
        // 关闭输入流
        writer.close ();
    
        // 获取运行结果
        String tmp;
        StringBuilder sb = new StringBuilder ();
        while ((tmp = reader.readLine ()) != null) {
            sb.append (new String (tmp.getBytes ())).append ("\n");
        }
        reader.close ();
        
        // 等待 process 运行完毕, 关闭
        process.waitFor ();
        process.destroy ();
        return sb.toString ();
    }
    
    /**
     * 获取编译结果
     *
     * @param process 指令对象
     * @return {@link Process} 指令对象
     */
    public String compileCode(Process process) throws Exception {
        SequenceInputStream sis = new SequenceInputStream (process.getInputStream (), process.getErrorStream ());
        BufferedReader reader = new BufferedReader (new InputStreamReader (sis, "GBK"));
    
        // 获取编译结果
        String tmp;
        StringBuilder sb = new StringBuilder ();
        while ((tmp = reader.readLine ()) != null) {
            sb.append (new String (tmp.getBytes ())).append ("\n");
        }
    
        reader.close ();
        // 等待 process 运行完毕, 关闭
        process.waitFor ();
        process.destroy ();
        return sb.toString ();
    }
    
    /**
     * 定时任务, 清理上一小时的代码文件夹
     */
    @Scheduled(cron = "0 5 * * * ?")
    public void cleanDir () {
        DateTime lastHour = DateUtil.offsetHour (DateUtil.beginOfHour (new Date ()), -1);
        File path = new File ("/tmp/yicode/" + DateUtil.format (lastHour, "yyyy-MM-dd-HH"));
        
        FileUtil.del (path);
    }
}
