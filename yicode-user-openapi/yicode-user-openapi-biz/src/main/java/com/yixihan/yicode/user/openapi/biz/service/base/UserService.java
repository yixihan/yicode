package com.yixihan.yicode.user.openapi.biz.service.base;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.user.openapi.api.vo.request.base.*;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserDetailInfoVO;

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
     * @return {@link UserDetailInfoVO}
     */
    UserDetailInfoVO getUserInfo();

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
     * @param req     请求参数
     */
    CommonVO<Boolean> bindEmail(EmailReq req);

    /**
     * 解绑邮箱
     *
     * @param req     请求参数
     */
    CommonVO<Boolean> unbindEmail(EmailReq req);

    /**
     * 绑定手机号
     *
     * @param req     请求参数
     */
    CommonVO<Boolean> bindMobile(MobileReq req);

    /**
     * 解绑手机号
     *
     * @param req     请求参数
     */
    CommonVO<Boolean> unbindMobile(MobileReq req);

    /**
     * 更换用户名
     *
     * @param req     请求参数
     */
    CommonVO<Boolean> resetUserName(ResetUserNameReq req);

    /**
     * 用户注销
     *
     */
    CommonVO<Boolean> cancellation();
}
