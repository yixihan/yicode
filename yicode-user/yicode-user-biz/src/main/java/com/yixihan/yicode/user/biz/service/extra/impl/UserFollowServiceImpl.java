package com.yixihan.yicode.user.biz.service.extra.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.extra.FollowQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFollowDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FollowDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserFollowService;
import com.yixihan.yicode.user.dal.mapper.extra.UserFollowMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserFollow;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        UserFollow follow = CopyUtils.copySingle (UserFollow.class, dtoReq);
        int modify = baseMapper.insert (follow);
        if (modify == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public CommonDtoResult<Boolean> unfollowUser(ModifyFollowDtoReq dtoReq) {
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserFollow.USER_ID, dtoReq.getUserId ())
                .eq (UserFollow.FOLLOW_USER_ID, dtoReq.getFollowUserId ());
        int modify = baseMapper.delete (wrapper);
        if (modify == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public CommonDtoResult<Integer> getFollowCount(Long userId) {
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserFollow.USER_ID, userId);
        
        return new CommonDtoResult<> (
                Optional.ofNullable (baseMapper.selectCount (wrapper))
                        .orElse (NumConstant.NUM_0)
        );
    }

    @Override
    public PageDtoResult<FollowDtoResult> getFollowList(FollowQueryDtoReq dtoReq) {
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserFollow.FOLLOW_USER_ID, dtoReq.getUserId ());
        Page<UserFollow> values = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize ()),
                wrapper
        );
    
        return PageUtil.pageToPageDtoResult (
                values,
                (o) -> CopyUtils.copySingle (FollowDtoResult.class, o)
        );
    }

    @Override
    public CommonDtoResult<Integer> getFanCount(Long userId) {
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserFollow.FOLLOW_USER_ID, userId);
    
        return new CommonDtoResult<> (
                Optional.ofNullable (baseMapper.selectCount (wrapper))
                        .orElse (NumConstant.NUM_0)
        );
    }

    @Override
    public PageDtoResult<FollowDtoResult> getFanList(FollowQueryDtoReq dtoReq) {
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserFollow.FOLLOW_USER_ID, dtoReq.getUserId ());
        Page<UserFollow> values = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize ()),
                wrapper
        );
        
        return PageUtil.pageToPageDtoResult (
                values,
                (o) -> CopyUtils.copySingle (FollowDtoResult.class, o)
        );
    }
}
