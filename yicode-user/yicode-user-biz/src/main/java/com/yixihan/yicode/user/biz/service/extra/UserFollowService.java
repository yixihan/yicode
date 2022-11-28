package com.yixihan.yicode.user.biz.service.extra;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.FollowQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFollowDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FollowDtoResult;
import com.yixihan.yicode.user.dal.pojo.extra.UserFollow;

/**
 * <p>
 * 用户关注表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
public interface UserFollowService extends IService<UserFollow> {

    /**
     * 关注用户
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> followUser(ModifyFollowDtoReq dtoReq);

    /**
     * 取消关注用户
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> unfollowUser(ModifyFollowDtoReq dtoReq);

    /**
     * 获取用户的关注者数量
     *
     * @param userId 用户 ID
     */
    CommonDtoResult<Integer> getFollowCount(Long userId);

    /**
     * 获取用户的关注者列表
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<FollowDtoResult> getFollowList(FollowQueryDtoReq dtoReq);

    /**
     * 获取用户的粉丝数量
     *
     * @param userId 用户 ID
     */
    CommonDtoResult<Integer> getFanCount(Long userId);

    /**
     * 获取用户的粉丝列表
     *
     * @param dtoReq 请求参数
     */
    PageDtoResult<FollowDtoResult> getFanList(FollowQueryDtoReq dtoReq);
}
