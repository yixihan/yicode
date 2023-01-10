package com.yixihan.yicode.run.web.controller;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import com.yixihan.yicode.run.api.reset.CodeRunApi;
import com.yixihan.yicode.run.biz.service.CodeRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 代码编译运行 服务实现类
 *
 * @author yixihan
 * @date 2023/1/3 15:21
 */
@Slf4j
@RestController
public class CodeRunController implements CodeRunApi {
    
    @Resource
    private CodeRunService service;
    
    @Override
    public ApiResult<CodeRunDtoResult> runCode (@RequestBody CodeRunDtoReq dtoReq) {
        return ApiResult.create (service.fetchCodeRunItem (dtoReq));
    }
}
