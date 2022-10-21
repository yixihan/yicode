package com.yixihan.yicode.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.auth.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
public interface UserService extends IService<User>, UserDetailsService {


    /**
     * 通过用户名获取用户对象
     *
     * @param userName username
     * @return {@link User}
     */
    User getUserByUserName(String userName);

    /**
     * 获取所有用户角色信息, 存入 redis 库
     * <br/>
     * 初始化时进行
     */
    void initUserRoleInfo ();
}
