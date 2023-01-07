package com.yixihan.yicode.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务统一异常类
 *
 * @author yixihan
 * @date 2022-10-10-13:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {

    /**
     * 异常状态码
     */
    private int errorCode;

    /**
     * 异常信息
     */
    private String errorMsg;

    public BizException() {
        super ();
    }
    
    public BizException(Exception e) {
        this.errorCode = 500;
        this.errorMsg = e.getMessage ();
    }

    public BizException(CommonError commonError) {
        super (commonError.getErrorMsg ());
        this.errorCode = commonError.getErrorCode ();
        this.errorMsg = commonError.getErrorMsg ();
    }

    public BizException(CommonError commonError, String msg) {
        super (msg);
        this.errorCode = commonError.getErrorCode ();
        this.errorMsg = msg;
    }

    public BizException(int code, String msg) {
        super (msg);
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public BizException(String msg) {
        super (msg);
        this.errorMsg = msg;
    }


}
