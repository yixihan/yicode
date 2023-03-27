package com.yixihan.yicode.user.openapi.biz.service.base;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.base.*;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserVO;

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
    UserDetailInfoVO getUserDetailInfo(Long userId);

    /**
     * 获取当前登录用户详细信息
     *
     * @return {@link UserDetailInfoVO}
     */
    UserDetailInfoVO getUserDetailInfo();
    
    /**
     * 获取当前登录用户信息
     *
     * @return {@link UserDtoResult}
     */
    UserDtoResult getUser ();
    
    /**
     * 获取当前登录用户 id
     *
     * @return 用户 id
     */
    Long getUserId ();
    
    /**
     * 获取指定 ID 用户信息
     *
     * @param userId 用户 ID
     * @return {@link UserDtoResult}
     */
    UserDtoResult getUser (Long userId);
    
    /**
     * 获取用户列表
     *
     * @param req 请求参数
     * @return {@link UserDetailInfoVO}
     */
    PageVO<UserVO> getUserList(QueryUserReq req);
    
    /**
     * 用户注册
     *
     * @param req 请求参数
     */
    void register(RegisterUserReq req);
    
    /**
     * 重置密码
     *
     * @param req 请求参数
     */
    void resetPassword(ResetPasswordReq req);
    
    /**
     * 绑定邮箱
     *
     * @param req     请求参数
     */
    void bindEmail(EmailReq req);
    
    /**
     * 解绑邮箱
     *
     * @param req     请求参数
     */
    void unbindEmail(EmailReq req);
    
    /**
     * 绑定手机号
     *
     * @param req     请求参数
     */
    void bindMobile(MobileReq req);
    
    /**
     * 解绑手机号
     *
     * @param req     请求参数
     */
    void unbindMobile(MobileReq req);
    
    /**
     * 更换用户名
     *
     * @param req     请求参数
     */
    void resetUserName(ResetUserNameReq req);
    
    /**
     * 用户注销
     *
     * @param userId 用户 ID
     */
    void cancellation(Long userId);
    
    /**
     * 校验用户 ID 是否存在
     *
     * @param userId 用户 ID
     * @return 存在 : true | 不存在 : false
     */
    Boolean verifyUserId (Long userId);
}
