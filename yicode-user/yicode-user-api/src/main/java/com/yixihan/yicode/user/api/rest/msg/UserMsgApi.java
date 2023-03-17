package com.yixihan.yicode.user.api.rest.msg;


import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.msg.AddMessageDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.MessageDetailDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.ReadMessageDtoReq;
import com.yixihan.yicode.user.api.dto.response.msg.MessageDetailDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户消息 api
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Api(tags = "用户消息 api")
@RequestMapping("/msg")
public interface UserMsgApi {
    
    @ApiOperation("添加一条消息")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<MessageDetailDtoResult> addMessage(@RequestBody AddMessageDtoReq dtoReq);
    
    @ApiOperation("阅读消息")
    @PostMapping(value = "/read", produces = "application/json")
    void readMessages(@RequestBody ReadMessageDtoReq dtoReq);
    
    @ApiOperation("获取消息明细")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<PageDtoResult<MessageDetailDtoResult>> messageDetail(@RequestBody MessageDetailDtoReq dtoReq);
    
    @ApiOperation("获取未读消息条数")
    @PostMapping(value = "/un-read/count", produces = "application/json")
    ApiResult<Integer> unReadMessageCount(@RequestBody Long userId);
    
    @ApiOperation("获取消息模板")
    @PostMapping(value = "/template", produces = "application/json")
    ApiResult<String> getMessageTemplate(@RequestBody String templateId);
    
}
