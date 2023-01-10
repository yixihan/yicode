package com.yixihan.yicode.user.web.controller.msg;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.msg.AddMessageDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.MessageDetailDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.ReadMessageDtoReq;
import com.yixihan.yicode.user.api.dto.response.msg.MessageDetailDtoResult;
import com.yixihan.yicode.user.api.rest.msg.UserMsgApi;
import com.yixihan.yicode.user.biz.service.msg.UserMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户消息表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Slf4j
@RestController
public class UserMsgController implements UserMsgApi {
    
    @Resource
    private UserMsgService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addMessage(AddMessageDtoReq dtoReq) {
        return ApiResult.create (service.addMessage (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> readMessages(ReadMessageDtoReq dtoReq) {
        return ApiResult.create (service.readMessages (dtoReq));
    }
    
    @Override
    public ApiResult<PageDtoResult<MessageDetailDtoResult>> messageDetail(MessageDetailDtoReq dtoReq) {
        return ApiResult.create (service.messageDetail (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Integer>> unReadMessageCount(Long userId) {
        return ApiResult.create (service.unReadMessageCount (userId));
    }
    
    @Override
    public ApiResult<CommonDtoResult<String>> getMessageTemplate(String templateId) {
        return ApiResult.create (service.getMessageTemplate (templateId));
    }
}
