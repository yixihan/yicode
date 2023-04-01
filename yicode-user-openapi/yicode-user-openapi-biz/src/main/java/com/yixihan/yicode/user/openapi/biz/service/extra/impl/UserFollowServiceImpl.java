package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.common.enums.MsgTypeEnums;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.user.api.dto.request.extra.FollowQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFollowDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FollowDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.FollowQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyFollowReq;
import com.yixihan.yicode.user.openapi.api.vo.request.msg.AddMessageReq;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FollowVO;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserFollowFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserFollowService;
import com.yixihan.yicode.user.openapi.biz.service.msg.UserMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户关注 服务实现类
 *
 * @author yixihan
 * @date 2022/12/21 9:45
 */
@Slf4j
@Service
public class UserFollowServiceImpl implements UserFollowService {
    
    @Resource
    private UserMsgService userMsgService;
    
    @Resource
    private UserFollowFeignClient followFeignClient;
    
    @Resource
    private UserService userService;
    
    @Override
    public void followUser(ModifyFollowReq req) {
        ModifyFollowDtoReq dtoReq = new ModifyFollowDtoReq (userService.getUser ().getUserId (), req.getFollowUserId ());
    
        // 关注
        followFeignClient.followUser (dtoReq);
        
        // 发送消息
        AddMessageReq messageReq = new AddMessageReq ();
        messageReq.setMessageType (MsgTypeEnums.FOLLOW.getType ());
        messageReq.setReceiveUseId (req.getFollowUserId ());
        userMsgService.addMessage (messageReq);
    }
    
    @Override
    public void unfollowUser(ModifyFollowReq req) {
        ModifyFollowDtoReq dtoReq = new ModifyFollowDtoReq (userService.getUser ().getUserId (), req.getFollowUserId ());
    
        // 取关
        followFeignClient.unfollowUser (dtoReq);
    }
    
    @Override
    public Integer getFollowCount(Long userId) {
        return followFeignClient.getFollowCount (userId).getResult ();
    }
    
    @Override
    public PageVO<FollowVO> getFollowList(FollowQueryReq req) {
        FollowQueryDtoReq dtoReq = BeanUtil.toBean (req, FollowQueryDtoReq.class);
        PageDtoResult<FollowDtoResult> dtoResult = followFeignClient.getFollowList (dtoReq).getResult ();
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, FollowVO.class)
        );
    }
    
    @Override
    public Integer getFanCount(Long userId) {
        return followFeignClient.getFanCount (userId).getResult ();
    }
    
    @Override
    public PageVO<FollowVO> getFanList(FollowQueryReq req) {
        FollowQueryDtoReq dtoReq = BeanUtil.toBean (req, FollowQueryDtoReq.class);
        PageDtoResult<FollowDtoResult> dtoResult = followFeignClient.getFanList (dtoReq).getResult ();
    
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, FollowVO.class)
        );
    }
}
