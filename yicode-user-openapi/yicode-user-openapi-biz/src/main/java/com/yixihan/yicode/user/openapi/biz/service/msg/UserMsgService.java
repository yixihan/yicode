package com.yixihan.yicode.user.openapi.biz.service.msg;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.AddMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.ReadMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.response.msg.MessageDetailVO;

/**
 * 消息模块 服务类
 *
 * @author yixihan
 * @date 2023/1/10 16:51
 */
public interface UserMsgService {
    
    /**
     * 添加一条消息
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> addMessage(AddMessageReq req);
    
    /**
     * 阅读消息
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> readMessages(ReadMessageReq req);
    
    /**
     * 获取消息明细
     *
     * @param req 请求参数
     */
    PageVO<MessageDetailVO> messageDetail(PageReq req);
    
    /**
     * 获取未读消息条数
     */
    CommonVO<Integer> unReadMessageCount();
    
}
