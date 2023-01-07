package com.yixihan.yicode.common.util;

import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Json 响应数据
 *
 * @author yixihan
 * @date 2022-09-29-14:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Json响应数据")
public class JsonResponse<T> implements Serializable {
    private static final long serialVersionUID = -7995058338163551623L;

    public static final int SUCCESS_CODE = 200;

    @ApiModelProperty("返回码：200为成功")
    private int code;

    @ApiModelProperty("异常信息")
    private String message;

    @ApiModelProperty("请求ID")
    private String requestId;

    @ApiModelProperty("返回数据")
    private T data;

    @ApiModelProperty("是否正常返回")
    private boolean success;

    @ApiModelProperty("响应参数")
    private Map<String, Object> params;

    private JsonResponse(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
        this.success = true;
    }

    private JsonResponse(int status, String message) {
        this.code = status == 0 ? SUCCESS_CODE : status;
        this.message = message;
        this.success = status == SUCCESS_CODE;
    }



    public void addParams(String key, Object value) {
        if (StrUtil.isBlank (key)) {
            return;
        }
        if (null == params) {
            params = new HashMap<> ();
        }
        params.put (key, value);
    }

    public static <T> JsonResponse<T> ok() {
        JsonResponse<T> jsonResponse = new JsonResponse<> ();
        jsonResponse.setCode (SUCCESS_CODE);
        jsonResponse.setSuccess (Boolean.TRUE);
        return jsonResponse;
    }

    public static <T> JsonResponse<T> ok(T data) {
        return new JsonResponse<> (data);
    }

    public static <T> JsonResponse<T> error(String message) {
        return new <T>JsonResponse<T> (BizCodeEnum.FAILED_TYPE_INTERNAL.getCode (), message);
    }

    public static <T> JsonResponse<T> error(BizCodeEnum err) {
        return new <T>JsonResponse<T>(err.getErrorCode (), err.getErrorMsg ());
    }

    public static <T> JsonResponse<T> error(T data, String message) {
        JsonResponse<T> jsonResponse = new JsonResponse<> ();
        jsonResponse.setCode (BizCodeEnum.FAILED_TYPE_INTERNAL.getCode ());
        jsonResponse.setSuccess (Boolean.FALSE);
        jsonResponse.setData (data);
        jsonResponse.setMessage (message);
        return jsonResponse;
    }

    public static <T> JsonResponse<T> error(int errorCode, String message) {
        return new JsonResponse<> (errorCode, message);
    }
    
    public static <T> JsonResponse<T> error (Exception e) {
        JsonResponse<T> response = new JsonResponse<> ();
        response.setCode (BizCodeEnum.FAILED_TYPE_INTERNAL.getCode ());
        response.setSuccess (Boolean.FALSE);
        response.setMessage (e.getMessage ());
        return response;
    }

    public static <T> JsonResponse<T> badRequest(String message) {
        return new JsonResponse<> (BizCodeEnum.FAILED_TYPE_BAD_REQUEST.getCode (), message);
    }
}
