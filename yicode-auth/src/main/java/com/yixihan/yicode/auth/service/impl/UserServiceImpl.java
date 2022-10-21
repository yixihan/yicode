package com.yixihan.yicode.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.auth.mapper.UserMapper;
import com.yixihan.yicode.auth.pojo.Role;
import com.yixihan.yicode.auth.pojo.User;
import com.yixihan.yicode.auth.service.UserRoleService;
import com.yixihan.yicode.auth.service.UserService;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUserName (username);

        if (user == null) {
            throw new UsernameNotFoundException (BizCodeEnum.USERNAME_PASSWORD_ERR.getMsg ());
        }

        if (!user.isEnabled ()) {
            throw new DisabledException (BizCodeEnum.ACCOUNT_DISABLED.getMsg ());
        } else if (!user.isAccountNonLocked ()) {
            throw new LockedException (BizCodeEnum.ACCOUNT_LOCKED.getMsg ());
        } else if (!user.isAccountNonExpired ()) {
            throw new AccountExpiredException (BizCodeEnum.ACCOUNT_EXPIRED.getMsg ());
        } else if (!user.isCredentialsNonExpired ()) {
            throw new CredentialsExpiredException (BizCodeEnum.CREDENTIALS_EXPIRED.getMsg ());
        }

        return user;
    }

    @Override
    public User getUserByUserName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq ("user_name", userName);
        return baseMapper.selectOne (wrapper);
    }

    @PostConstruct
    @Cacheable(cacheNames = "userRoleList", key = "userRoleList")
    public void initUserRoleInfo() {
        // 查询所有用户
        Map<Long, List<Role>> userRoleList = new HashMap<> ();
        baseMapper.selectList (null).stream ().map (User::getUserId).forEach (userId -> {
            userRoleList.put (userId, userRoleService.getUserRoleByUserId (userId));
        });
        log.info ("userRoleList = {}", userRoleList);
        redisTemplate.opsForValue ().set ("userRoleList", userRoleList);
    }
}
