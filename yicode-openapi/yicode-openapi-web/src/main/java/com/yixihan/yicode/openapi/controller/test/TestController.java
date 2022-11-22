package com.yixihan.yicode.openapi.controller.test;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.openapi.reset.test.TestOpenApi;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yixihan
 * @date 2022-10-14-13:40
 */
@Log
@RestController
public class TestController implements TestOpenApi {

    @Value ("${yicode.db.port}")
    private Integer prot;

    @Resource
    private HttpServletRequest httpServletRequest;

    @Override
    public JsonResponse<String> testGetYaml() {
        return JsonResponse.ok (prot.toString ());
    }

    @Override
    @SentinelResource(value = "testFlow", blockHandler = "flowBlockHandler")
    public JsonResponse<String> testFlow() {
        return JsonResponse.ok ("本次访问服务未限流");
    }

    @Override
    @SentinelResource(value = "testDegrade", blockHandler = "degradeBlockHandler")
    public JsonResponse<String> testDegrade() {
        return JsonResponse.ok ("本次访问服务未降级");
    }

    @Override
    public JsonResponse<String> testHttpServletRequest() {
        return JsonResponse.ok (httpServletRequest.getHeader ("test"));
    }

    public JsonResponse<String> flowBlockHandler (BlockException e) {
        return JsonResponse.error ("本次访问服务已被限流");
    }

    public JsonResponse<String> degradeBlockHandler (BlockException e) {
        return JsonResponse.error ("本次访问服务已被降级");
    }
}
