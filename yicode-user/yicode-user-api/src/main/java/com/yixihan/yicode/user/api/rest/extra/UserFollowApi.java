package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFollowDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FollowQueryDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FollowDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户关注 api
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Api(tags = "用户关注 api")
@RequestMapping("/follow/")
public interface UserFollowApi {

    @ApiOperation ("关注用户")
    @PostMapping(value = "/follow-user", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> followUser (@RequestBody ModifyFollowDtoReq dtoReq);

    @ApiOperation ("取消关注用户")
    @PostMapping(value = "/unfollow-user", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> unfollowUser (@RequestBody ModifyFollowDtoReq dtoReq);

    @ApiOperation ("获取关注数量")
    @PostMapping(value = "/count/follow/{userId}", produces = "application/json")
    ApiResult<CommonDtoResult<Integer>> getFollowCount (@PathVariable("userId") Long userId);

    @ApiOperation ("获取关注列表")
    @GetMapping(value = "/list/follow", produces = "application/json")
    ApiResult<PageDtoResult<FollowDtoResult>> getFollowList (@RequestBody FollowQueryDtoReq dtoReq);

    @ApiOperation ("获取粉丝数量")
    @PostMapping(value = "/count/fan/{userId}", produces = "application/json")
    ApiResult<CommonDtoResult<Integer>> getFanCount (@PathVariable("userId") Long userId);

    @ApiOperation ("获取粉丝列表")
    @PostMapping(value = "/list/fan", produces = "application/json")
    ApiResult<PageDtoResult<FollowDtoResult>> getFanList (@RequestBody FollowQueryDtoReq dtoReq);
}
