package com.yixihan.yicode.runcode.run.constant;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 代码运行 常量配置类
 *
 * @author yixihan
 * @date 2023/1/3 15:32
 */
@Component
@Getter
public class CodeRunConstant {
    
    @Value ("${yicode.code-run.file-dir}")
    private String fileDir;
    
    
}
