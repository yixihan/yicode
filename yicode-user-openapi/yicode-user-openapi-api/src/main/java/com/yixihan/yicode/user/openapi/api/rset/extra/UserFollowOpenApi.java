package com.yixihan.yicode.user.openapi.api.rset.extra;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.FollowQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyFollowReq;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FollowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户关注 OpenApi
 *
 * @author yixihan
 * @date 2022/12/21 9:37
 */
@Api(tags = "用户关注 OpenApi")
@RequestMapping("/open/user/follow")
public interface UserFollowOpenApi {
    
    @ApiOperation("关注用户")
    @PostMapping(value = "/follow-user", produces = "application/json")
    void followUser (@RequestBody ModifyFollowReq req);
    
    @ApiOperation ("取消关注用户")
    @PostMapping(value = "/unfollow-user", produces = "application/json")
    void unfollowUser (@RequestBody ModifyFollowReq req);
    
    @ApiOperation ("获取关注数量")
    @GetMapping(value = "/follow/count", produces = "application/json")
    JsonResponse<Integer> getFollowCount (@RequestParam("userId") Long userId);
    
    @ApiOperation ("获取关注列表")
    @PostMapping(value = "/follow/detail", produces = "application/json")
    JsonResponse<PageVO<FollowVO>> getFollowList (@RequestBody FollowQueryReq req);
    
    @ApiOperation ("获取粉丝数量")
    @GetMapping(value = "/fan/count", produces = "application/json")
    JsonResponse<Integer> getFanCount (@RequestParam("userId") Long userId);
    
    @ApiOperation ("获取粉丝列表")
    @PostMapping(value = "/fan/detail", produces = "application/json")
    JsonResponse<PageVO<FollowVO>> getFanList (@RequestBody FollowQueryReq req);
}
