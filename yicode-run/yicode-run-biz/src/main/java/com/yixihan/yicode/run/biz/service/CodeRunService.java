package com.yixihan.yicode.run.biz.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.util.SnowFlake;
import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
            CodeRunExtractService service = SpringUtil.getBean (CodeRunConfig.getServiceName (req.getCodeType ()), CodeRunExtractService.class);
            
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
    public String decodeCode(String code) {
        return Base64.decodeStr (code);
    }
    
    /**
     * 获取代码存放目录
     *
     * @return 代码存放目录
     */
    public File getPath() {
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
     * 公共方法, 创建代码源文件
     *
     * @param code     代码
     * @param fileName 文件名
     * @return 代码源文件
     */
    public File createFile(String code, String fileName) {
        // 生成源代码目录
        File path = new File (getPath () + "/" + SnowFlake.nextId ());
        FileUtil.mkdir (path);
        // 生成源代码文件
        File file = new File (path + "/" + fileName);
        // 写入代码
        writeCode (code, file);
        
        return file;
    }
    
    /**
     * 公共方法, 编译、运行代码<br>
     * 需提前创建代码源文件
     *
     * @param req            请求参数
     * @param runCommand     运行命令
     * @return 代码运行结果 {@link CodeRunDtoResult}
     */
    public CodeRunDtoResult run(@NotNull CodeRunDtoReq req, @NotNull String[] runCommand) throws Exception {
        // 运行代码
        List<String> ansList = new ArrayList<> ();
        
        long useTime = 0;
        for (String param : req.getParamList ()) {
            
            Process process = Runtime.getRuntime ().exec (runCommand);
            
            // 传入形参
            try (BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (process.getOutputStream ()))) {
                writer.write (Base64.decodeStr (param));
            }
            
            // 获取开始时间
            long startTime = System.currentTimeMillis ();
            
            // 等待 process 运行完毕
            int modify = process.waitFor ();
            
            // 获取消耗时间
            useTime += System.currentTimeMillis () - startTime;
            
            // 获取运行结果
            // 正常运行输出结果
            SequenceInputStream sis = new SequenceInputStream (process.getInputStream (), process.getErrorStream ());
            BufferedReader reader = new BufferedReader (new InputStreamReader (sis, StandardCharsets.UTF_8));
            String tmp;
            StringBuilder sb = new StringBuilder ();
            while ((tmp = reader.readLine ()) != null) {
                sb.append (new String (tmp.getBytes ())).append ("\n");
            }
            
            if (modify != NumConstant.NUM_0) {
                // 运行出现异常
                return new CodeRunDtoResult (
                        CollUtil.newArrayList (sb.toString ()),
                        Boolean.TRUE, Boolean.FALSE, null, null
                );
            }
            
            process.destroy ();
            ansList.add (sb.toString ());
        }
        return new CodeRunDtoResult (ansList, Boolean.TRUE, Boolean.TRUE, useTime, 0D);
    }
    
    /**
     * 公共代码, 编译代码源文件
     *
     * @param compileCommand 编译命令
     * @return 若编译失败, 则返回编译失败原因; 编译成功则返回一个空串
     */
    public String compile(String[] compileCommand) throws Exception {
        Process process = Runtime.getRuntime ().exec (compileCommand);
        // 等待编译完成
        int modify = process.waitFor ();
    
        // 获取编译结果
        // 正常编译输出结果
        // 正常运行输出结果
        SequenceInputStream sis = new SequenceInputStream (process.getInputStream (), process.getErrorStream ());
        BufferedReader reader = new BufferedReader (new InputStreamReader (sis, StandardCharsets.UTF_8));
        String tmp;
        StringBuilder sb = new StringBuilder ();
        while ((tmp = reader.readLine ()) != null) {
            sb.append (new String (tmp.getBytes ())).append ("\n");
        }
    
        // 销毁进程
        process.destroy ();
        // 如果异常输出流内有内容, 则程序编译失败
        return modify != NumConstant.NUM_0 ? sb.toString () : null;
    }
    
    
    /**
     * 定时任务, 清理上一小时的代码文件夹
     */
    @Scheduled(cron = "0 5 * * * ?")
    public void cleanDir() {
        DateTime lastHour = DateUtil.offsetHour (DateUtil.beginOfHour (new Date ()), -1);
        File path = new File ("/tmp/yicode/" + DateUtil.format (lastHour, "yyyy-MM-dd-HH"));
        
        FileUtil.del (path);
    }
}
