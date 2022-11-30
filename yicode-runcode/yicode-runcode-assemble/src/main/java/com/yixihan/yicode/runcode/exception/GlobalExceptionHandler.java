package com.yixihan.yicode.runcode.exception;

import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.exception.RRException;
import com.yixihan.yicode.common.util.JsonResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 通用业务异常捕获
     *
     */
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public JsonResponse<Object> handleBizException (BizException e) {
        return JsonResponse.error (e.getErrorCode (), e.getErrorMsg ());
    }

    /**
     * 自定义异常捕获
     *
     */
    @ResponseBody
    @ExceptionHandler(value = RRException.class)
    public JsonResponse<Object> handleRRException (RRException e) {
        return JsonResponse.error (e.getCode (), e.getMessage ());
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
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return JsonResponse.error (BizCodeEnum.PARAMS_VALID_ERR.getErrorCode (), message);
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
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return JsonResponse.error (BizCodeEnum.PARAMS_VALID_ERR.getErrorCode (), message);
    }


    @ResponseBody
    @ExceptionHandler(value = NullPointerException.class)
    public JsonResponse<Object> handleNullPointerException (NullPointerException e) {
        return JsonResponse.error (BizCodeEnum.NULL_ERR);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonResponse<Object> handleException (Exception e) {
        return JsonResponse.error (BizCodeEnum.FAILED_TYPE_INTERNAL, e.getMessage ());
    }
}
