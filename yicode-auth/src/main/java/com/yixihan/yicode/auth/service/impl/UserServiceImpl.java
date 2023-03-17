package com.yixihan.yicode.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.auth.feign.thirdpart.code.PhotoCodeFeignClient;
import com.yixihan.yicode.auth.feign.thirdpart.email.EmailFeignClient;
import com.yixihan.yicode.auth.feign.thirdpart.sms.SMSFeignClient;
import com.yixihan.yicode.auth.feign.user.user.UserFeignClient;
import com.yixihan.yicode.auth.feign.user.user.UserRoleFeignClient;
import com.yixihan.yicode.auth.pojo.Role;
import com.yixihan.yicode.auth.pojo.User;
import com.yixihan.yicode.auth.service.UserService;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private SMSFeignClient smsFeignClient;

    @Resource
    private EmailFeignClient emailFeignClient;

    @Resource
    private PhotoCodeFeignClient codeFeignClient;

    @Resource
    private UserRoleFeignClient userRoleFeignClient;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean flag = false;
        // 如果是采用非账号密码的登录方式
        if (username.contains ("~~other")) {
            flag = true;
            username = username.substring (0, username.length () - 7);
        }
        User user = findUserByUserName (username);
        if (flag) {
            user.setUserPassword (passwordEncoder.encode (user.getPassword ()));
        }
        if (user == null) {
            throw new BizException (BizCodeEnum.USERNAME_PASSWORD_ERR);
        }

        if (!user.isEnabled ()) {
            throw new BizException (BizCodeEnum.ACCOUNT_DISABLED);
        } else if (!user.isAccountNonLocked ()) {
            throw new BizException (BizCodeEnum.ACCOUNT_LOCKED);
        } else if (!user.isAccountNonExpired ()) {
            throw new BizException (BizCodeEnum.ACCOUNT_EXPIRED);
        } else if (!user.isCredentialsNonExpired ()) {
            throw new BizException (BizCodeEnum.CREDENTIALS_EXPIRED);
        }

        return user;
    }

    @CachePut(cacheNames = "user", key = "#userName")
    @Override
    public User findUserByUserName(String userName) {
        UserDtoResult userDtoResult = userFeignClient.getUserByUserName (userName).getResult ();
        return getUser (userDtoResult);
    }

    @Override
    public User findUserByMobile(String mobile) {
        UserDtoResult userDtoResult = userFeignClient.getUserByMobile (mobile).getResult ();
        return getUser (userDtoResult);
    }

    @Override
    public User findUserByEmail(String email) {
        UserDtoResult userDtoResult = userFeignClient.getUserByEmail (email).getResult ();
        return getUser (userDtoResult);
    }

    @Override
    public User validateEmailCode(EmailValidateDtoReq dtoReq) {
        emailFeignClient.validate (dtoReq);
        return findUserByEmail (dtoReq.getEmail ());
    }

    @Override
    public User validateMobileCode(SMSValidateDtoReq dtoReq) {
        smsFeignClient.validate (dtoReq);
       
        return findUserByMobile (dtoReq.getMobile ());
    }

    @Override
    public Boolean validatePhotoCode(CodeValidateDtoReq dtoReq) {
        codeFeignClient.validate (dtoReq);
        return true;
    }

    private User getUser (UserDtoResult userDtoResult) {
        List<RoleDtoResult> roleDtoResults = userRoleFeignClient.getUserRoleList (userDtoResult.getUserId ()).getResult ();
        User user = BeanUtil.toBean (userDtoResult, User.class);
        List<Role> userRoleList = BeanUtil.copyToList (roleDtoResults, Role.class);
        user.setAuthorities (userRoleList);
        return user;
    }
}
