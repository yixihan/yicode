package com.yixihan.yicode.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
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
     * 获取用户信息
     *
     * @param userId 用户 id
     * @return {@link UserDetailInfoDtoResult}
     */
    UserDetailInfoDtoResult getUserInfo(Long userId);
}
