package com.yixihan.yicode.user.openapi.biz.service.extra;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.FollowQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyFollowReq;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FollowVO;

/**
 * 用户关注 服务类
 *
 * @author yixihan
 * @date 2022/12/21 9:45
 */
public interface UserFollowService {
    
    /**
     * 关注用户
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> followUser(ModifyFollowReq req);
    
    /**
     * 取消关注用户
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> unfollowUser(ModifyFollowReq req);
    
    /**
     * 获取关注者数量
     *
     * @param userId 用户 ID
     */
    CommonVO<Integer> getFollowCount(Long userId);
    
    /**
     * 获取关注者列表
     *
     * @param req 请求参数
     */
    PageVO<FollowVO> getFollowList(FollowQueryReq req);
    
    /**
     * 获取粉丝数量
     *
     * @param userId 用户 ID
     */
    CommonVO<Integer> getFanCount(Long userId);
    
    /**
     * 获取粉丝列表
     *
     * @param req 请求参数
     */
    PageVO<FollowVO> getFanList(FollowQueryReq req);
}
