package com.yixihan.yicode.user.biz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.dal.pojo.base.User;

import java.util.List;

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

    /**
     * 通过 mobile 获取用户信息
     *
     * @param mobile 手机号
     * @return {@link UserDtoResult}
     */
    UserDtoResult getUserByMobile(String mobile);


    /**
     * 通过 email 获取用户信息
     *
     * @param email 邮箱
     * @return {@link UserDtoResult}
     */
    UserDtoResult getUserByEmail(String email);

    /**
     * 通过缓存 （token） 获取用户信息
     * @param token tokne
     * @return {@link UserDtoResult}
     */
    UserDtoResult getUserByToken(String token);

    /**
     * 用户注册
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> register(RegisterUserDtoReq dtoReq);

    /**
     * 重置密码
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> resetPassword(ResetPasswordDtoReq dtoReq);

    /**
     * 绑定邮箱
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> bindEmail(BindEmailDtoReq dtoReq);

    /**
     * 解绑邮箱
     *
     * @param userId 用户 ID
     */
    CommonDtoResult<Boolean> unbindEmail(Long userId);

    /**
     * 绑定手机号
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> bindMobile(BindMobileDtoReq dtoReq);

    /**
     * 解绑手机号
     *
     * @param userId 用户 ID
     */
    CommonDtoResult<Boolean> unbindMobile(Long userId);

    /**
     * 更改用户名
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> resetUserName(ResetUserNameDtoReq dtoReq);

    /**
     * 用户注销
     *
     * @param userId 用户 ID
     */
    CommonDtoResult<Boolean> cancellation(Long userId);

    /**
     * 校验用户名
     *
     * @param userName 用户名
     */
    CommonDtoResult<Boolean> verifyUserName (String userName);

    /**
     * 校验邮箱
     *
     * @param email 邮箱
     */
    CommonDtoResult<Boolean> verifyUserEmail (String email);

    /**
     * 校验手机号
     *
     * @param mobile 手机号
     */
    CommonDtoResult<Boolean> verifyUserMobile (String mobile);
    
    /**
     * 获取用户常用信息
     *
     * @param userIdList 用户 ID 列表
     * @return List {@link UserCommonDtoResult}
     */
    List<UserCommonDtoResult> getUserCommonInfo (List<Long> userIdList);
}
