package com.yixihan.yicode.runcode.run.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.yixihan.yicode.runcode.run.constant.CodeRunConstant;
import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
    
    @Resource
    private CodeRunConstant constant;
    
    
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
    public List<String> fetchCodeRunItem(CodeRunDtoReq req) {
        List<String> ansList = new ArrayList<> ();
        try {
            if (req == null) {
                return Collections.emptyList ();
            }
    
            // 提取服务
            CodeRunExtractService service = SpringUtil.getBean (
                    CodeRunConfig.getServiceName (req.getCodeType ()),
                    CodeRunExtractService.class
            );
    
            // 运行代码
            ansList = service.run (req);
        } catch (Exception e) {
            log.error (e.getMessage ());
            ansList.add (e.getMessage ());
        }
        return ansList;
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
        return new File (constant.getFileDir () +
                "/" + DateUtil.format (new Date (), "yyyy-MM-dd-HH"));
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
     * 编译运行代码, 获取运行结果
     *
     * @param process 指令对象
     * @param params 传参
     * @return {@link Process} 指令对象
     */
    public String runProcess(Process process, List<String> params) {
        StringBuilder sb = new StringBuilder ();
        try (BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (process.getOutputStream ()));
             SequenceInputStream sis = new SequenceInputStream (process.getInputStream (), process.getErrorStream ());
             BufferedReader reader = new BufferedReader (new InputStreamReader (sis, "gbk"))
        ) {
            log.info ("start write input params...");
            for (String param : params) {
                writer.write (param);
                log.info ("param : {}", param);
                writer.newLine ();
            }
            log.info ("input param success!");
        
            String tmp;
            sb = new StringBuilder ();
            log.info ("get output now...");
            while ((tmp = reader.readLine ()) != null) {
                log.info ("output : {}", tmp);
                sb.append (new String (tmp.getBytes ())).append ("\n");
            }
            log.info ("output get success!");
        } catch (IOException e) {
            e.printStackTrace ();
            sb.append (e.getMessage ());
            return sb.toString ();
        }
        return sb.toString ();
    }
    
    /**
     * 定时任务, 清理上一小时的代码文件夹
     */
    @Scheduled(cron = "0 5 * * * ?")
    public void cleanDir () {
        DateTime lastHour = DateUtil.offsetHour (DateUtil.beginOfHour (new Date ()), -1);
        File path = new File (constant.getFileDir () +
                "/" + DateUtil.format (lastHour, "yyyy-MM-dd-HH"));
        
        FileUtil.del (path);
    }
}
