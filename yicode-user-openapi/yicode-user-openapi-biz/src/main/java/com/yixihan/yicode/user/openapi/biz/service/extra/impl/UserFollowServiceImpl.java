package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
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
 * 用户收藏 服务实现类
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
        return null;
    }
    
    @Override
    public CommonVO<Boolean> unfollowUser(ModifyFollowReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Integer> getFollowCount(Long userId) {
        return null;
    }
    
    @Override
    public PageVO<FollowVO> getFollowList(FollowQueryReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Integer> getFanCount(Long userId) {
        return null;
    }
    
    @Override
    public PageVO<FollowVO> getFanList(FollowQueryReq req) {
        return null;
    }
}
