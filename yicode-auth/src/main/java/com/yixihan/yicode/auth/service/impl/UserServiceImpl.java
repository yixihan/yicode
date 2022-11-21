package com.yixihan.yicode.auth.service.impl;

import com.yixihan.yicode.auth.feign.*;
import com.yixihan.yicode.auth.pojo.Role;
import com.yixihan.yicode.auth.pojo.User;
import com.yixihan.yicode.auth.service.UserService;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.CodeValidateDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SMSValidateDtoReq;
import com.yixihan.yicode.user.api.dto.response.RoleDtoResult;
import com.yixihan.yicode.user.api.dto.response.UserDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
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
    private CodeFeignClient codeFeignClient;

    @Resource
    private UserRoleFeignClient userRoleFeignClient;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        Boolean result = emailFeignClient.validate (dtoReq).getResult ().getData ();
        if (!result) {
            return null;
        }
        return findUserByEmail (dtoReq.getEmail ());
    }

    @Override
    public User validateMobileCode(SMSValidateDtoReq dtoReq) {
        Boolean result = smsFeignClient.validate (dtoReq).getResult ().getData ();
        if (!result) {
            return null;
        }
        return findUserByMobile (dtoReq.getMobile ());
    }

    @Override
    public Boolean validatePhotoCode(CodeValidateDtoReq dtoReq) {
        return codeFeignClient.validateCode (dtoReq).getResult ().getData ();
    }

    private User getUser (UserDtoResult userDtoResult) {
        List<RoleDtoResult> roleDtoResults = userRoleFeignClient.getUserRoleList (userDtoResult.getUserId ()).getResult ();
        User user = CopyUtils.copySingle (User.class, userDtoResult);
        List<Role> userRoleList = CopyUtils.copyMulti (Role.class, roleDtoResults);
        user.setAuthorities (userRoleList);
        return user;
    }
}
