package com.yixihan.yicode.user.openapi.biz.service;

import com.yixihan.yicode.user.openapi.api.vo.response.UserDetailInfoVO;

/**
 * @author yixihan
 * @date 2022-10-22-18:06
 */
public interface UserService {

    /**
     * 获取用户详细信息
     *
     * @param userId 用户 id
     * @return
     */
    UserDetailInfoVO getUserInfo(Long userId);
}
