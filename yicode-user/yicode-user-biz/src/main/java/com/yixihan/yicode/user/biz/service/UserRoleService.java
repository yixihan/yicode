package com.yixihan.yicode.user.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.user.dal.pojo.Role;
import com.yixihan.yicode.user.dal.pojo.UserRole;

import java.util.List;

/**
 * <p>
 * 用户角色对应表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 获取用户角色信息
     *
     * @param userId 用户 id
     * @return
     */
    List<Role> getUserRoleByUserId(Long userId);
}
