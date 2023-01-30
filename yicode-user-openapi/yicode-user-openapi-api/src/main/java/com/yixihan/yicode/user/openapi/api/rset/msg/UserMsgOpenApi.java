package com.yixihan.yicode.user.openapi.api.rset.msg;


import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.AddMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.ReadMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.response.msg.MessageDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户消息 OpenApi
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Api(tags = "用户消息 OpenApi")
@RequestMapping("/open/msg")
public interface UserMsgOpenApi {
    
    @ApiOperation("添加一条消息")
    @PostMapping(value = "/add", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> addMessage(@RequestBody AddMessageReq req);
    
    @ApiOperation("阅读消息")
    @PostMapping(value = "/read", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> readMessages(@RequestBody ReadMessageReq req);
    
    @ApiOperation("获取消息明细")
    @PostMapping(value = "/detail", produces = "application/json")
    JsonResponse<PageVO<MessageDetailVO>> messageDetail(@RequestBody PageReq req);
    
    @ApiOperation("获取未读消息条数")
    @GetMapping(value = "/un-read/count", produces = "application/json")
    JsonResponse<CommonVO<Integer>> unReadMessageCount();
}
