package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.FollowQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFollowDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FollowDtoResult;
import com.yixihan.yicode.user.api.rest.extra.UserFollowApi;
import com.yixihan.yicode.user.biz.service.extra.UserFollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户关注表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Slf4j
@RestController
public class UserFollowController implements UserFollowApi {

    @Resource
    private UserFollowService service;

    @Override
    public void followUser(ModifyFollowDtoReq dtoReq) {
        service.followUser (dtoReq);
    }

    @Override
    public void unfollowUser(ModifyFollowDtoReq dtoReq) {
        service.unfollowUser (dtoReq);
    }

    @Override
    public ApiResult<Integer> getFollowCount(Long userId) {
        return ApiResult.create (service.getFollowCount (userId));
    }

    @Override
    public ApiResult<PageDtoResult<FollowDtoResult>> getFollowList(FollowQueryDtoReq dtoReq) {
        return ApiResult.create (service.getFollowList (dtoReq));
    }

    @Override
    public ApiResult<Integer> getFanCount(Long userId) {
        return ApiResult.create (service.getFanCount (userId));
    }

    @Override
    public ApiResult<PageDtoResult<FollowDtoResult>> getFanList(FollowQueryDtoReq dtoReq) {
        return ApiResult.create (service.getFanList (dtoReq));
    }
}
