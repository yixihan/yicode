package com.yixihan.yicode.thirdpart.openapi.biz.constant;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yixihan
 * @date 2022-10-26-11:31
 */
@Getter
@Component
public class CodeConstant {

    @Value ("${yicode.thirdpart.code.length}")
    private Integer codeLen;

    @Value ("${yicode.thirdpart.code.time-out}")
    private Integer timeOut;
}
