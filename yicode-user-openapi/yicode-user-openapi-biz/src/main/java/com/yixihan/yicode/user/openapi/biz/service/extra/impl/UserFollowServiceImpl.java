package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.user.api.dto.request.extra.FollowQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFollowDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FollowDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.FollowQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyFollowReq;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FollowVO;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserFollowFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserFollowService;
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
    private UserFollowFeignClient followFeignClient;
    
    @Resource
    private UserService userService;
    
    @Override
    public CommonVO<Boolean> followUser(ModifyFollowReq req) {
        // 校验 userId
        if (!userService.verifyUserId (req.getFollowUserId ())) {
            throw new BizException ("关注者不存在!");
        }
    
        ModifyFollowDtoReq dtoReq = new ModifyFollowDtoReq (userService.getUser ().getUserId (), req.getFollowUserId ());
    
        CommonDtoResult<Boolean> dtoResult = followFeignClient.followUser (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> unfollowUser(ModifyFollowReq req) {
        // 校验 userId
        if (!userService.verifyUserId (req.getFollowUserId ())) {
            throw new BizException ("关注者不存在!");
        }
    
        ModifyFollowDtoReq dtoReq = new ModifyFollowDtoReq (userService.getUser ().getUserId (), req.getFollowUserId ());
    
        CommonDtoResult<Boolean> dtoResult = followFeignClient.unfollowUser (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Integer> getFollowCount(Long userId) {
        // 校验 userId
        if (!userService.verifyUserId (userId)) {
            return new CommonVO<> (NumConstant.NUM_0);
        }
    
        CommonDtoResult<Integer> dtoResult = followFeignClient.getFollowCount (userId).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public PageVO<FollowVO> getFollowList(FollowQueryReq req) {
        // 校验 userId
        if (!userService.verifyUserId (req.getUserId ())) {
            return new PageVO<> ();
        }
    
        FollowQueryDtoReq dtoReq = new FollowQueryDtoReq (req.getUserId ());
        PageDtoResult<FollowDtoResult> dtoResult = followFeignClient.getFollowList (dtoReq).getResult ();
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, FollowVO.class)
        );
    }
    
    @Override
    public CommonVO<Integer> getFanCount(Long userId) {
        // 校验 userId
        if (!userService.verifyUserId (userId)) {
            return new CommonVO<> (NumConstant.NUM_0);
        }
    
        CommonDtoResult<Integer> dtoResult = followFeignClient.getFanCount (userId).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public PageVO<FollowVO> getFanList(FollowQueryReq req) {
        // 校验 userId
        if (!userService.verifyUserId (req.getUserId ())) {
            return new PageVO<> ();
        }
    
        FollowQueryDtoReq dtoReq = new FollowQueryDtoReq (req.getUserId ());
        PageDtoResult<FollowDtoResult> dtoResult = followFeignClient.getFanList (dtoReq).getResult ();
    
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, FollowVO.class)
        );
    }
}
