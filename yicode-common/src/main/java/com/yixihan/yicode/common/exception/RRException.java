package com.yixihan.yicode.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义异常类
 *
 * @author yixihan
 * @date 2022-10-10-13:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RRException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public RRException(String msg) {
        super (msg);
        this.msg = msg;
    }

    public RRException(String msg, Throwable e) {
        super (msg, e);
        this.msg = msg;
    }

    public RRException(String msg, int code) {
        super (msg);
        this.msg = msg;
        this.code = code;
    }

    public RRException(String msg, int code, Throwable e) {
        super (msg, e);
        this.msg = msg;
        this.code = code;
    }

}
