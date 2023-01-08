package com.yixihan.yicode.runcode.run.controller;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.runcode.run.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.runcode.run.dto.response.CodeRunDtoResult;
import com.yixihan.yicode.runcode.run.service.CodeRunService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CodeRunController {
    
    @Resource
    private CodeRunService service;
    
    @PostMapping("/code")
    public ApiResult<CodeRunDtoResult> runCode (@RequestBody CodeRunDtoReq dtoReq) {
        return ApiResult.create (service.fetchCodeRunItem (dtoReq));
    }
}
