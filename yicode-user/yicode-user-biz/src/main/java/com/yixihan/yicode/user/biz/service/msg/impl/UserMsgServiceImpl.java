package com.yixihan.yicode.user.biz.service.msg.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.msg.AddMessageDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.MessageDetailDtoReq;
import com.yixihan.yicode.user.api.dto.request.msg.ReadMessageDtoReq;
import com.yixihan.yicode.user.api.dto.response.msg.MessageDetailDtoResult;
import com.yixihan.yicode.user.biz.service.msg.TemplateMsgService;
import com.yixihan.yicode.user.biz.service.msg.UserMsgService;
import com.yixihan.yicode.user.dal.mapper.msg.UserMsgMapper;
import com.yixihan.yicode.user.dal.pojo.msg.UserMsg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户消息表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Service
public class UserMsgServiceImpl extends ServiceImpl<UserMsgMapper, UserMsg> implements UserMsgService {
    
    @Resource
    private TemplateMsgService templateMsgService;
    
    @Override
    public MessageDetailDtoResult addMessage(AddMessageDtoReq dtoReq) {
        UserMsg msg = BeanUtil.toBean (dtoReq, UserMsg.class);
    
        // 保存
        Assert.isTrue (save (msg), BizCodeEnum.FAILED_TYPE_INTERNAL);

        return BeanUtil.toBean (msg, MessageDetailDtoResult.class);
    }
    
    @Override
    public void readMessages(ReadMessageDtoReq dtoReq) {
        boolean modify = lambdaUpdate ()
                .eq (UserMsg::getReceiveUseId, dtoReq.getUserId ())
                .in (CollUtil.isNotEmpty (dtoReq.getMessageIdList ()),
                        UserMsg::getId, dtoReq.getMessageIdList ())
                .set (CollUtil.isNotEmpty (dtoReq.getMessageIdList ()),
                        UserMsg::getFinish, NumConstant.NUM_1)
                .update ();
    
        Assert.isTrue (modify, BizCodeEnum.FAILED_TYPE_INTERNAL);
    }
    
    @Override
    public PageDtoResult<MessageDetailDtoResult> messageDetail(MessageDetailDtoReq dtoReq) {
        Page<UserMsg> pages = lambdaQuery ()
                .eq (UserMsg::getReceiveUseId, dtoReq.getUserId ())
                .orderByDesc (UserMsg::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
        
        return PageUtil.pageToPageDtoResult (
                pages,
                o -> BeanUtil.toBean (o, MessageDetailDtoResult.class)
        );
    }
    
    @Override
    public Integer unReadMessageCount(Long userId) {
        return lambdaQuery ()
                .eq (UserMsg::getReceiveUseId, userId)
                .eq (UserMsg::getFinish, NumConstant.NUM_0)
                .count ();
    }
    
    @Override
    public String getMessageTemplate(String templateId) {
        return templateMsgService.getMessageTemplate (templateId);
    }
}
