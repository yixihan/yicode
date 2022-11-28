package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
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
    private UserFollowService userFollowService;

    @Override
    public ApiResult<CommonDtoResult<Boolean>> followUser(ModifyFollowDtoReq dtoReq) {
        return ApiResult.create (userFollowService.followUser (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> unfollowUser(ModifyFollowDtoReq dtoReq) {
        return ApiResult.create (userFollowService.unfollowUser (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Integer>> getFollowCount(Long userId) {
        return ApiResult.create (userFollowService.getFollowCount (userId));
    }

    @Override
    public ApiResult<PageDtoResult<FollowDtoResult>> getFollowList(FollowQueryDtoReq dtoReq) {
        return ApiResult.create (userFollowService.getFollowList (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Integer>> getFanCount(Long userId) {
        return ApiResult.create (userFollowService.getFanCount (userId));
    }

    @Override
    public ApiResult<PageDtoResult<FollowDtoResult>> getFanList(FollowQueryDtoReq dtoReq) {
        return ApiResult.create (userFollowService.getFanList (dtoReq));
    }
}
