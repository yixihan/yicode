package com.yixihan.yicode.user.openapi.web.controller.extra;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.extra.UserFollowOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.FollowQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyFollowReq;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FollowVO;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserFollowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户关注 前端控制器
 *
 * @author yixihan
 * @date 2022/12/21 9:40
 */
@Slf4j
@RestController
public class UserFollowController implements UserFollowOpenApi {
    
    @Resource
    private UserFollowService service;
    
    @Override
    public void followUser(ModifyFollowReq req) {
        service.followUser (req);
    }
    
    @Override
    public void unfollowUser(ModifyFollowReq req) {
        service.unfollowUser (req);
    }
    
    @Override
    public JsonResponse<Integer> getFollowCount(Long userId) {
        return JsonResponse.ok (service.getFollowCount (userId));
    }
    
    @Override
    public JsonResponse<PageVO<FollowVO>> getFollowList(FollowQueryReq req) {
        return JsonResponse.ok (service.getFollowList (req));
    }
    
    @Override
    public JsonResponse<Integer> getFanCount(Long userId) {
        return JsonResponse.ok (service.getFanCount (userId));
    }
    
    @Override
    public JsonResponse<PageVO<FollowVO>> getFanList(FollowQueryReq req) {
        return JsonResponse.ok (service.getFanList (req));
    }
}
