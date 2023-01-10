package com.yixihan.yicode.user.biz.service.msg;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.user.api.dto.request.msg.AddMessageDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.MessageDetailDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.ReadMessageDtoReq;
import com.yixihan.yicode.user.api.dto.response.msg.MessageDetailDtoResult;
import com.yixihan.yicode.user.dal.pojo.msg.UserMsg;

/**
 * <p>
 * 用户消息表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
public interface UserMsgService extends IService<UserMsg> {
    
    /**
     * 添加一条消息
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addMessage(AddMessageDtoReq dtoReq);
    
    /**
     * 阅读消息
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> readMessages(ReadMessageDtoReq dtoReq);
    
    /**
     * 获取消息明细
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<MessageDetailDtoResult> messageDetail(MessageDetailDtoReq dtoReq);
    
    /**
     * 获取未阅读消息数量
     *
     * @param userId 用户 ID
     */
    CommonDtoResult<Integer> unReadMessageCount(Long userId);
    
    /**
     * 获取消息模板
     *
     * @param templateId 消息模板 ID
     */
    CommonDtoResult<String> getMessageTemplate(String templateId);
    
}
