package com.yixihan.yicode.auth.service.impl;

import com.yixihan.yicode.auth.feign.UserFeignClient;
import com.yixihan.yicode.auth.pojo.User;
import com.yixihan.yicode.auth.service.UserService;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.response.UserDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUserName (username);

        if (user == null) {
            throw new UsernameNotFoundException (BizCodeEnum.USERNAME_PASSWORD_ERR.getMsg ());
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

    @Override
    public User getUserByUserName(String userName) {
        UserDtoResult user = userFeignClient.getUserByUserName (userName).getResult ();
        return CopyUtils.copySingle (User.class, user);
    }
}
