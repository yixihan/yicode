package com.yixihan.yicode.user.openapi.web.controller.msg;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.msg.UserMsgOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.AddMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.ReadMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.response.msg.MessageDetailVO;
import com.yixihan.yicode.user.openapi.biz.service.msg.UserMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户消息 前端控制器
 *
 * @author yixihan
 * @date 2023/1/10 16:50
 */
@Slf4j
@RestController
public class UserMsgController implements UserMsgOpenApi {
    
    @Resource
    private UserMsgService service;
    
    @Override
    public JsonResponse<CommonVO<Boolean>> addMessage(AddMessageReq req) {
        return JsonResponse.ok (service.addMessage(req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> readMessages(ReadMessageReq req) {
        return JsonResponse.ok (service.readMessages(req));
    }
    
    @Override
    public JsonResponse<PageVO<MessageDetailVO>> messageDetail(PageReq req) {
        return JsonResponse.ok (service.messageDetail(req));
    }
    
    @Override
    public JsonResponse<CommonVO<Integer>> unReadMessageCount() {
        return JsonResponse.ok (service.unReadMessageCount());
    }
}
