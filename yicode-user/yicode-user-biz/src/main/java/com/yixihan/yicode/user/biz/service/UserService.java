package com.yixihan.yicode.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.UserDtoResult;
import com.yixihan.yicode.user.dal.pojo.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
public interface UserService extends IService<User> {

    /**
     * 获取用户详细信息
     *
     * @param userId 用户 id
     * @return {@link UserDetailInfoDtoResult}
     */
    UserDetailInfoDtoResult getUserInfo(Long userId);

    /**
     * 通过 userId 获取用户信息
     *
     * @param userId 用户 id
     * @return {@link UserDtoResult}
     */
    UserDtoResult getUserById(Long userId);

    /**
     * 通过用户名获取用户信息
     *
     * @param userName 用户名
     * @return {@link UserDtoResult}
     */
    UserDtoResult getUserByUserName(String userName);
}
