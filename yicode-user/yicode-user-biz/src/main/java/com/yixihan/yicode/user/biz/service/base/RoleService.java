package com.yixihan.yicode.user.biz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.dal.pojo.base.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
public interface RoleService extends IService<Role> {
    
    /**
     * 通过 角色id 获取角色信息-列表查询
     *
     * @param roleIdList 角色 id
     * @return Role 集合
     */
    List<Role> getRoleList(List<Long> roleIdList);
    
    /**
     * 获取所有角色列表
     */
    List<RoleDtoResult> getRoleList();
    
    /**
     * 添加角色
     *
     * @param roleName 角色命
     */
    CommonDtoResult<Boolean> addRole(String roleName);
    
    /**
     * 删除角色
     *
     * @param roleId 角色 ID
     */
    CommonDtoResult<Boolean> delRole(Long roleId);
    
    /**
     * 判断有无此角色 ID
     *
     * @param roleId 角色 ID
     * @return 有：false | 无：true
     */
    Boolean hasRole (Long roleId);
    
}
