package com.yixihan.yicode.question.openapi.api.prop;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author yixihan
 * @date 2023/1/29 14:09
 */
@Component
@Getter
public class CodeJudgeProp {
    
    @Value ("${judge.max-output-len}")
    private Integer maxOutputLen;
    
    @Value ("${judge.max-run-time}")
    private Integer maxRunTime;
    
    
}
