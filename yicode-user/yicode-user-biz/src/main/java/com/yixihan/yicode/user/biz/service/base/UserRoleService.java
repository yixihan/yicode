package com.yixihan.yicode.user.biz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.user.api.dto.request.base.ModifyUserRoleDtoReq;
import com.yixihan.yicode.user.api.dto.request.base.UserRoleQueryDtoReq;
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
     * 添加用户角色
     *
     * @param dtoReq 请求参数
     * @return 用户角色列表 {@link RoleDtoResult}
     */
    List<RoleDtoResult> addRole(ModifyUserRoleDtoReq dtoReq);
    
    /**
     * 删除用户角色
     *
     * @param dtoReq 请求参数
     * @return 用户角色列表 {@link RoleDtoResult}
     */
    List<RoleDtoResult> delRole(ModifyUserRoleDtoReq dtoReq);
    
    /**
     * 获取用户的角色信息-列表查询
     *
     * @param userId 用户 id
     * @return 用户角色列表 {@link RoleDtoResult}
     */
    List<RoleDtoResult> getUserRoleByUserId(Long userId);

    /**
     * 获取用户的角色信息-分页查询
     *
     * @param dtoReq 请求参数
     * @return 用户角色列表 {@link RoleDtoResult}
     */
    PageDtoResult<RoleDtoResult> getUserRoleByUserId(UserRoleQueryDtoReq dtoReq);
}
