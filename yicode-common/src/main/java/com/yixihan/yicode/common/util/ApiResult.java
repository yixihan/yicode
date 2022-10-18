package com.yixihan.yicode.common.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Cloud Api单值包装, 可序列化和反序列化
 *
 * @author yixihan
 * @date 2022-09-29-14:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -2763957428943891790L;

    private T result;

    public static <E> ApiResult<E> create(E instance) {
        return new ApiResult<> (instance);
    }

    public static <E> ApiResult<E> ok() {
        return new ApiResult<E> ();
    }

}
