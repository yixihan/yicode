package com.yixihan.yicode.auth.service;

import com.yixihan.yicode.auth.pojo.User;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
public interface UserService extends UserDetailsService {


    /**
     * 通过用户名获取用户对象
     *
     * @param userName username
     * @return {@link User}
     */
    User findUserByUserName(String userName);

    /**
     * 通过手机号获取用户对象
     *
     * @param mobile 手机号
     * @return {@link User}
     */
    User findUserByMobile (String mobile);

    /**
     * 通过邮箱获取用户对象
     *
     * @param email 邮箱
     * @return {@link User}
     */
    User findUserByEmail (String email);

    /**
     * 校验邮箱验证码
     *
     * @param dtoReq
     * @return
     */
    User validateEmailCode (EmailValidateDtoReq dtoReq);

    /**
     * 校验手机验证码
     *
     * @param dtoReq
     * @return
     */
    User validateMobileCode (SMSValidateDtoReq dtoReq);

    /**
     * 校验图片验证码
     */
    Boolean validatePhotoCode (CodeValidateDtoReq dtoReq);

}
