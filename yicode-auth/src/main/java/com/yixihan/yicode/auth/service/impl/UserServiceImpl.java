package com.yixihan.yicode.auth.service.impl;

import com.yixihan.yicode.auth.service.UserService;
import com.yixihan.yicode.auth.pojo.User;
import com.yixihan.yicode.auth.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
