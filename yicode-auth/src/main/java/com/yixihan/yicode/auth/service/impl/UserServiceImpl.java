package com.yixihan.yicode.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.auth.mapper.UserMapper;
import com.yixihan.yicode.auth.pojo.User;
import com.yixihan.yicode.auth.service.UserService;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUserName (username);

        if (user == null) {
            throw new UsernameNotFoundException (BizCodeEnum.USERNAME_PASSWORD_ERR.getMsg ());
        }

        if (!user.isEnabled()) {
            throw new DisabledException (BizCodeEnum.ACCOUNT_DISABLED.getMsg ());
        } else if (!user.isAccountNonLocked()) {
            throw new LockedException (BizCodeEnum.ACCOUNT_LOCKED.getMsg ());
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException (BizCodeEnum.ACCOUNT_EXPIRED.getMsg ());
        } else if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException (BizCodeEnum.CREDENTIALS_EXPIRED.getMsg ());
        }

        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq ("user_name", userName);
        return userMapper.selectOne (wrapper);
    }
}
