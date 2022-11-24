package com.yixihan.yicode.openapi.controller.test;

import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.exception.RRException;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.openapi.reset.test.TestExceptionOpenApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.net.BindException;

/**
 * description
 *
 * @author yixihan
 * @date 2022/11/24 10:17
 */
@Slf4j
@RestController
public class TestExceptionController implements TestExceptionOpenApi {


    @Override
    public JsonResponse<String> testBizException() {
        throw new BizException (BizCodeEnum.FAILED_TYPE_INTERNAL);
    }

    @Override
    public JsonResponse<String> testRRException() {
        throw new RRException ("RRException");
    }

    @Override
    public JsonResponse<String> testBindException() {
        try {
            throw new BindException ("bind exception");
        } catch (BindException e) {
            throw new RuntimeException (e);
        }
    }

    @Override
    public JsonResponse<String> testNullPointerException() {
        throw new NullPointerException ("null pointer exception");
    }

    @Override
    public JsonResponse<String> testException() {
        try {
            throw new Exception ();
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }
}
