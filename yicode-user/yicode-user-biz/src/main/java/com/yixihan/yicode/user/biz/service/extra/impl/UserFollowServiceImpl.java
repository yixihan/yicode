package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.extra.FollowQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFollowDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.FollowDtoResult;
import com.yixihan.yicode.user.biz.service.base.UserService;
import com.yixihan.yicode.user.biz.service.extra.UserFollowService;
import com.yixihan.yicode.user.dal.mapper.extra.UserFollowMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserFollow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Resource
    private UserService userService;
    
    @Override
    public void followUser(ModifyFollowDtoReq dtoReq) {
        UserFollow follow = BeanUtil.toBean (dtoReq, UserFollow.class);
    
        // 保存
        Assert.isTrue (save (follow), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }

    @Override
    public void unfollowUser(ModifyFollowDtoReq dtoReq) {
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserFollow.USER_ID, dtoReq.getUserId ())
                .eq (UserFollow.FOLLOW_USER_ID, dtoReq.getFollowUserId ());
    
        Assert.isTrue (remove (wrapper), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }

    @Override
    public Integer getFollowCount(Long userId) {
        return lambdaQuery ()
                .eq (UserFollow::getUserId, userId)
                .count ();
    }

    @Override
    public PageDtoResult<FollowDtoResult> getFollowList(FollowQueryDtoReq dtoReq) {
        Page<UserFollow> values = lambdaQuery ()
                .eq (UserFollow::getUserId, dtoReq.getUserId ())
                .orderByDesc (UserFollow::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
    
        return setUserCommonInfo (values);
    }
    
    @Override
    public Integer getFanCount(Long userId) {
        return lambdaQuery ()
                .eq (UserFollow::getFollowUserId, userId)
                .count ();
    }
    
    @Override
    public PageDtoResult<FollowDtoResult> getFanList(FollowQueryDtoReq dtoReq) {
        Page<UserFollow> values = lambdaQuery ()
                .eq (UserFollow::getFollowUserId, dtoReq.getUserId ())
                .orderByDesc (UserFollow::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
    
        return setUserCommonInfo (values);
    }
    
    /**
     * 设置用户常用信息 (用户名+密码)
     *
     * @param values Page values
     * @return PageDtoResult values
     */
    private PageDtoResult<FollowDtoResult> setUserCommonInfo(Page<UserFollow> values) {
        // 获取用户 id 列表
        List<Long> userIdList = values.getRecords ()
                .stream ()
                .flatMap (item -> Stream.of (item.getUserId (), item.getFollowUserId ()))
                .distinct ()
                .collect (Collectors.toList ());
    
        // 获取用户关注着基础信息
        Map<Long, UserCommonDtoResult> userCommonInfoMap = userService.getUserCommonInfo (userIdList)
                .stream ()
                .collect (Collectors.toMap (
                        UserCommonDtoResult::getUserId,
                        Function.identity (),
                        (key1, key2) -> key1));
        
        // 转为 PageDtoResult 格式
        PageDtoResult<FollowDtoResult> pageDtoResult = PageUtil.pageToPageDtoResult (
                values,
                o -> BeanUtil.toBean (o, FollowDtoResult.class)
        );
        
        // 设置用户名+用户头像
        pageDtoResult.getRecords ().parallelStream ().forEach (item -> {
            UserCommonDtoResult followCommonInfo = userCommonInfoMap
                    .getOrDefault (item.getFollowUserId (), new UserCommonDtoResult ());
            UserCommonDtoResult userCommonInfo = userCommonInfoMap
                    .getOrDefault (item.getUserId (), new UserCommonDtoResult ());
    
            item.setFollowUserName (followCommonInfo.getUserName ());
            item.setFollowUserAvatar (followCommonInfo.getUserAvatar ());
            item.setUserName (userCommonInfo.getUserName ());
            item.setUserAvatar (userCommonInfo.getUserAvatar ());
        });
        
        return pageDtoResult;
    }
}
