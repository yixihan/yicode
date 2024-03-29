package com.yixihan.yicode.message.api.rest;

import com.yixihan.yicode.message.api.dto.request.MsgSendDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测评系统 api
 *
 * @author yixihan
 * @date 2023/1/9 17:04
 */
@Api(tags = "测评系统 api")
@RequestMapping("/task")
public interface TaskApi {
    
    @ApiOperation("提交代码")
    @PostMapping(value = "/commit", produces = "application/json")
    void commit(@RequestBody MsgSendDtoReq dtoReq);
    
    @ApiOperation("自测代码")
    @PostMapping(value = "/test", produces = "application/json")
    void test(@RequestBody MsgSendDtoReq dtoReq);
}
