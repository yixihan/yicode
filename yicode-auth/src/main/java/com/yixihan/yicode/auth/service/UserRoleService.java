package com.yixihan.yicode.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.auth.pojo.Role;
import com.yixihan.yicode.auth.pojo.UserRole;

import java.util.List;

/**
 * <p>
 * 用户角色对应表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 通过用户 id 获取用户角色信息
     * @param userId
     * @return
     */
    List<String> getUserRoleByUserId (Long userId);


}
