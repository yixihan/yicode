package com.yixihan.yicode.user.biz.service.extra.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.FollowQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFollowDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FollowDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserFollowService;
import com.yixihan.yicode.user.dal.mapper.extra.UserFollowMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserFollow;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户关注表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollow> implements UserFollowService {

    @Override
    public CommonDtoResult<Boolean> followUser(ModifyFollowDtoReq dtoReq) {
        return null;
    }

    @Override
    public CommonDtoResult<Boolean> unfollowUser(ModifyFollowDtoReq dtoReq) {
        return null;
    }

    @Override
    public CommonDtoResult<Integer> getFollowCount(Long userId) {
        return null;
    }

    @Override
    public PageDtoResult<FollowDtoResult> getFollowList(FollowQueryDtoReq dtoReq) {
        return null;
    }

    @Override
    public CommonDtoResult<Integer> getFanCount(Long userId) {
        return null;
    }

    @Override
    public PageDtoResult<FollowDtoResult> getFanList(FollowQueryDtoReq dtoReq) {
        return null;
    }
}
