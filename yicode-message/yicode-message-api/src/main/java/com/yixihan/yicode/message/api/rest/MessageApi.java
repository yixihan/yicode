package com.yixihan.yicode.message.api.rest;

import com.yixihan.yicode.message.api.dto.request.MsgSendDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 消息系统 api
 *
 * @author yixihan
 * @date 2023/1/9 17:04
 */
@Api(tags = "消息系统 api")
@RequestMapping("/message")
public interface MessageApi {
    
    @ApiOperation("发送消息")
    @PostMapping(value = "/send", produces = "application/json")
    void send(@RequestBody MsgSendDtoReq dtoReq);
}
