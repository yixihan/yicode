package com.yixihan.yicode.runcode.run.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.SnowFlake;
import com.yixihan.yicode.runcode.run.constant.CodeRunConstant;
import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.runcode.run.enums.CodeTypeEnums;
import com.yixihan.yicode.runcode.run.service.CodeRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * 代码编译运行 服务实现类
 *
 * @author yixihan
 * @date 2023/1/3 14:19
 */
@Slf4j
@Service
public class CodeRunServiceImpl implements CodeRunService {
    
    @Resource
    private CodeRunConstant constant;
    
    @Override
    public Process getPyProcess(File file) throws IOException {
        String command = "/bin/bash /c py " + file.getAbsolutePath ();
        System.out.println (command);
        Runtime run = Runtime.getRuntime ();
        
        return run.exec (command);
    }
    
    @Override
    public Process getCProcess(File file) throws IOException {
        String outName = file.getAbsolutePath ().substring (0, file.getAbsolutePath ().length () - 2);
        String command = "/bin/bash /c gcc " + file.getAbsolutePath () + " -o " + outName + " && " + outName + ".exe";
        System.out.println (command);
        Runtime run = Runtime.getRuntime ();
        return run.exec (command);
    }
    
    @Override
    public Process getJavaProcess(File file) throws IOException {
        String outName = file.getParent ();
        String command = "/bin/bash /c javac -encoding utf-8 " + file.getAbsolutePath () + " && cd " + outName + " && java Solution";
        System.out.println (command);
        Runtime run = Runtime.getRuntime ();
        return run.exec (command);
    }
    
    @Override
    public Process getCppProcess(File file) throws IOException {
        String outName = file.getAbsolutePath ().substring (0, file.getAbsolutePath ().length () - 4);
        String command = "/bin/bash /c g++ " + file.getAbsolutePath () + " -o " + outName + " && " + outName + ".exe";
        System.out.println (command);
        Runtime run = Runtime.getRuntime ();
        return run.exec (command);
    }
    
    @Override
    public Process getJsProcess(File file) throws IOException {
        String command = "/bin/bash /c node " + file.getAbsolutePath ();
        System.out.println (command);
        Runtime run = Runtime.getRuntime ();
        return run.exec (command);
    }
    
    @Override
    public Process getGoProcess(File file) throws IOException {
        String command = "/bin/bash /c go run " + file.getAbsolutePath ();
        System.out.println (command);
        Runtime run = Runtime.getRuntime ();
        return run.exec (command);
    }
    
    @Override
    public String runProcess(Process process, List<String> params) throws IOException {
        StringBuilder sb;
        BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (process.getOutputStream ()));
        SequenceInputStream sis = new SequenceInputStream (process.getInputStream (), process.getErrorStream ());
        BufferedReader reader = new BufferedReader (new InputStreamReader (sis, "GBK"));
        
        for (String param : params) {
            writer.write (param);
            writer.newLine ();
        }
        
        writer.close ();
        
        String tmp;
        sb = new StringBuilder ();
        
        while ((tmp = reader.readLine ()) != null) {
            
            sb.append (new String (tmp.getBytes ())).append ("\n");
        }
        
        reader.close ();
        
        return sb.toString ();
    }
    
    @Override
    public String runCode(CodeRunDtoReq dtoReq) {
        StringBuilder sb = new StringBuilder ();
        try {
            // 解码 code
            dtoReq.setCode (Base64.decodeStr (dtoReq.getCode ()));
            
            // 生成 Process
            Process process = getProcess (dtoReq);
    
            // 运行代码
            for (List<String> params : dtoReq.getParamList ()) {
                String ans = runProcess (process, params);
                sb.append (ans);
            }
        } catch (IOException e) {
            log.error ("执行代码出错, message : {}", e.getMessage ());
            throw new BizException (e.getMessage ());
        }
        
        return sb.toString ();
    }
    
    /**
     * 生成 Process 对象
     *
     * @param dtoReq 请求参数
     * @return {@link Process} 对象
     */
    private Process getProcess(CodeRunDtoReq dtoReq) throws IOException {
        CodeTypeEnums codeType = CodeTypeEnums.valueOf (dtoReq.getCodeType ());
        
            // 生成临时文件
            File file = createFile (dtoReq.getCode (), codeType.getType ());
            
            // 获取 Process 对象
            switch (codeType) {
                case C: {
                    return getCProcess (file);
                }
                case CPP: {
                    return getCppProcess (file);
                }
                case JAVA: {
                    return getJavaProcess (file);
                }
                case JS: {
                    return getJsProcess (file);
                }
                case PY: {
                    return getPyProcess (file);
                }
                case GO: {
                    return getGoProcess (file);
                }
                default: {
                    throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
                }
            }
        
    }
    
    /**
     * 生成代码源文件
     *
     * @param code 代码
     * @param type 代码类型 {@link CodeTypeEnums}
     * @return 代码源文件
     */
    private File createFile(String code, String type) {
        // 生成目录, 目录名为当前日期
        File path = new File (constant.getFileDir () +
                "/" + DateUtil.format (new Date (), "yyyy-MM-dd-HH") + "/" +
                SnowFlake.nextId ());
        if (!path.exists ()) {
            boolean b = path.mkdirs ();
            if (!b) {
                throw new BizException (BizCodeEnum.FAILED_TYPE_BUSINESS);
            }
        }
        
        // 生成文件, 文件名为随机生成
        File file = new File (path + "/" + SnowFlake.nextId () + "." + type);
        
        // 写入代码
        try (BufferedWriter bufferedWriter = new BufferedWriter (new FileWriter (file.getPath ()))) {
            bufferedWriter.write (code);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        
        return file;
    }
    
    
    /**
     * 定时任务, 清理上一小时的代码文件夹
     */
    @Scheduled(cron = "0 1 * * * ?")
    public void cleanDir () {
        DateTime lastHour = DateUtil.offsetHour (DateUtil.beginOfHour (new Date ()), -1);
        File path = new File (constant.getFileDir () +
                "/" + DateUtil.format (lastHour, "yyyy-MM-dd-HH"));
    
        FileUtil.del (path);
    }
}
