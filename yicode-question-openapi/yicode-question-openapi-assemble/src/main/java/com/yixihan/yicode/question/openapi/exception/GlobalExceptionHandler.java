package com.yixihan.yicode.question.openapi.exception;

import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.exception.RRException;
import com.yixihan.yicode.common.util.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ExceptionHandler(value = BizException.class)
    public JsonResponse<Object> handleBizException (BizException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e);
    }
    
    /**
     * 自定义异常捕获
     *
     */
    @ResponseBody
    @ExceptionHandler(value = RRException.class)
    public JsonResponse<Object> handleRRException (RRException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e);
    }
    
    /**
     * 参数校验异常捕获
     * <br>
     * 针对表单格式校验异常
     *
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public JsonResponse<Object> handleValidException(MethodArgumentNotValidException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e);
    }
    
    /**
     * 参数校验异常捕获
     * <br>
     * 针对 json 格式校验异常
     *
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public JsonResponse<Object> handleValidException(BindException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e);
    }
    
    
    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public JsonResponse<Object> handleNullPointerException (NullPointerException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e);
    }
    
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public JsonResponse<Object> handleRuntimeException (RuntimeException e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e);
    }
    
    
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonResponse<Object> handleException (Exception e) {
        log.error ("出现异常", e);
        return JsonResponse.error (e);
    }
}
