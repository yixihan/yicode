package com.yixihan.yicode.user.openapi.biz.service;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.user.openapi.api.vo.request.*;
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
     * @return {@link UserDetailInfoVO}
     */
    UserDetailInfoVO getUserInfo(Long userId);

    /**
     * 获取当前登录用户详细信息
     *
     * @param request request
     * @return {@link UserDetailInfoVO}
     */
    UserDetailInfoVO getUserInfo(HttpServletRequest request);

    /**
     * 用户注册
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> register(RegisterUserReq req);

    /**
     * 重置密码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> resetPassword(ResetPasswordReq req);

    /**
     * 绑定邮箱
     *
     * @param request request
     * @param req     请求参数
     */
    CommonVO<Boolean> bindEmail(HttpServletRequest request, BindEmailReq req);

    /**
     * 解绑邮箱
     *
     * @param request request
     */
    CommonVO<Boolean> unbindEmail(HttpServletRequest request);

    /**
     * 绑定手机号
     *
     * @param request request
     * @param req     请求参数
     */
    CommonVO<Boolean> bindMobile(HttpServletRequest request, BindMobileReq req);

    /**
     * 解绑手机号
     *
     * @param request request
     */
    CommonVO<Boolean> unbindMobile(HttpServletRequest request);

    /**
     * 更换用户名
     *
     * @param request request
     * @param req     请求参数
     */
    CommonVO<Boolean> resetUserName(HttpServletRequest request, ResetUserNameReq req);

    /**
     * 用户注销
     *
     * @param request request
     */
    CommonVO<Boolean> cancellation(HttpServletRequest request);
}
