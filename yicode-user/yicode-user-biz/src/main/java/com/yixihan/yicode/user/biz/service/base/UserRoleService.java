package com.yixihan.yicode.user.biz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.enums.RoleEnums;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.dal.pojo.base.UserRole;

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
    List<RoleDtoResult> getUserRoleByUserId(Long userId);

    /**
     * 添加用户角色
     *
     * @param userId 用户 ID
     * @param role 角色名 {@link RoleEnums}
     */
    CommonDtoResult<Boolean> addRole(Long userId, String role);

    /**
     * 删除用户角色
     *
     * @param userId 用户 ID
     * @param role 角色名 {@link RoleEnums}
     */
    CommonDtoResult<Boolean> delRole(Long userId, String role);

}
