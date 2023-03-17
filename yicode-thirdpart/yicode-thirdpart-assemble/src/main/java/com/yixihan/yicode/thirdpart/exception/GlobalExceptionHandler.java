package com.yixihan.yicode.thirdpart.exception;

import cn.hutool.json.JSONUtil;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.exception.RRException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常捕获器
 *
 * @author yixihan
 * @date 2022-10-23-14:31
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    
    
    /**
     * 通用业务异常捕获
     *
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = BizException.class)
    public String handleBizException (BizException e) {
        log.error ("出现异常", e);
        return JSONUtil.toJsonStr (new BizException (e));
    }
    
    /**
     * 自定义异常捕获
     *
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RRException.class)
    public String handleRRException (RRException e) {
        log.error ("出现异常", e);
        return JSONUtil.toJsonStr (new BizException (e));
    }
    
    /**
     * 参数校验异常捕获
     * <br>
     * 针对表单格式校验异常
     *
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String handleValidException(MethodArgumentNotValidException e) {
        log.error ("出现异常", e);
        return JSONUtil.toJsonStr (new BizException (e));
    }
    
    /**
     * 参数校验异常捕获
     * <br>
     * 针对 json 格式校验异常
     *
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = BindException.class)
    public String handleValidException(BindException e) {
        log.error ("出现异常", e);
        return JSONUtil.toJsonStr (new BizException (e));
    }
    
    
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = NullPointerException.class)
    public String handleNullPointerException (NullPointerException e) {
        log.error ("出现异常", e);
        return JSONUtil.toJsonStr (new BizException (e));
    }
    
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public String handleRuntimeException (RuntimeException e) {
        log.error ("出现异常", e);
        return JSONUtil.toJsonStr (new BizException (e));
    }
    
    
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String handleException (Exception e) {
        log.error ("出现异常", e);
        return JSONUtil.toJsonStr (new BizException (e));
    }
}
