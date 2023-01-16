package com.yixihan.yicode.user.biz.service.msg.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
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
import java.util.Optional;

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
    public CommonDtoResult<Boolean> addMessage(AddMessageDtoReq dtoReq) {
        UserMsg msg = BeanUtil.toBean (dtoReq, UserMsg.class);
        int modify = baseMapper.insert (msg);
        if (modify != NumConstant.NUM_1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_INTERNAL.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public CommonDtoResult<Boolean> readMessages(ReadMessageDtoReq dtoReq) {
        UpdateWrapper<UserMsg> wrapper = new UpdateWrapper<UserMsg> ()
                .eq (UserMsg.RECEIVE_USE_ID, dtoReq.getUserId ())
                .in (CollectionUtil.isNotEmpty (dtoReq.getMessageIdList ()), UserMsg.ID, dtoReq.getMessageIdList ())
                .set (CollectionUtil.isNotEmpty (dtoReq.getMessageIdList ()), UserMsg.FINISH, NumConstant.NUM_1);
    
        int modify = baseMapper.update (null, wrapper);
        if (modify != dtoReq.getMessageIdList ().size ()) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_INTERNAL.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public PageDtoResult<MessageDetailDtoResult> messageDetail(MessageDetailDtoReq dtoReq) {
        QueryWrapper<UserMsg> wrapper = new QueryWrapper<UserMsg> ()
                .eq (UserMsg.RECEIVE_USE_ID, dtoReq.getUserId ());
        
        Page<UserMsg> pages = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize (), dtoReq.getSearchCount ()),
                wrapper
        );
        
        return PageUtil.pageToPageDtoResult (
                pages,
                o -> BeanUtil.toBean (o, MessageDetailDtoResult.class)
        );
    }
    
    @Override
    public CommonDtoResult<Integer> unReadMessageCount(Long userId) {
        QueryWrapper<UserMsg> wrapper = new QueryWrapper<UserMsg> ()
                .eq (UserMsg.RECEIVE_USE_ID, userId)
                .eq (UserMsg.FINISH, NumConstant.NUM_0);
    
        Integer count = Optional.ofNullable (baseMapper.selectCount (wrapper)).orElse (NumConstant.NUM_0);
        return new CommonDtoResult<> (count);
    }
    
    @Override
    public CommonDtoResult<String> getMessageTemplate(String templateId) {
        return new CommonDtoResult<> (templateMsgService.getMessageTemplate (templateId));
    }
}
