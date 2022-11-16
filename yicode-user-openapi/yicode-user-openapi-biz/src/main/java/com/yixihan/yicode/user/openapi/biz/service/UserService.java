package com.yixihan.yicode.user.openapi.biz.service;

import com.yixihan.yicode.user.openapi.api.vo.response.UserDetailInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户 服务类
 *
 * @author yixihan
 * @date 2022-10-22-18:06
 */
public interface UserService {

    /**
     * 获取用户详细信息
     *
     * @param userId 用户 ID
     * @return
     */
    UserDetailInfoVO getUserInfo(Long userId);

    /**
     * 获取当前登录用户详细信息
     *
     * @param request request
     * @return
     */
    UserDetailInfoVO getUserInfo(HttpServletRequest request);
}
