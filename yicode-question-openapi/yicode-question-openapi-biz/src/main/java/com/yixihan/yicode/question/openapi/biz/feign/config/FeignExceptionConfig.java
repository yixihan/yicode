package com.yixihan.yicode.question.openapi.biz.feign.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * OpenFeign 异常捕获器
 *
 * @author yixihan
 * @date 2023/3/17 9:22
 */
@Slf4j
@Configuration
public class FeignExceptionConfig implements ErrorDecoder {
    
    @Override
    public Exception decode(String methodKey, Response response) {
        if (HttpStatus.OK.value () != response.status ()) {
            log.warn ("OpenFeign 调用出现异常!, methodKey : {}, status : {}", methodKey, response.status ());
            
            JSONObject json = JSONUtil.parseObj (response.body().toString ());
            return new BizException (json.getInt ("errorCode"), json.getStr ("errorMsg"));
        }
        
        return new BizException (BizCodeEnum.FAILED_TYPE_INTERNAL);
    }
}
