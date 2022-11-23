package com.yixihan.yicode.thirdpart.api.constant.code;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 验证码常量
 *
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

    // **** redis key **** //

    @Value ("${yicode.thirdpart.code.common-key}")
    private String commonKey;
}
