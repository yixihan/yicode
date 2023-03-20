package com.yixihan.yicode.user.biz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
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
     * 添加角色
     *
     * @param roleName 角色名称
     * @return {@link RoleDtoResult}
     */
    RoleDtoResult addRole(String roleName);
    
    /**
     * 删除角色
     *
     * @param roleId 角色 id
     */
    void delRole(Long roleId);
    
    /**
     * 判断有无此角色 id
     *
     * @param roleId 角色 id
     * @return 有：true | 无：false
     */
    Boolean validateRole(Long roleId);
    
    /**
     * 获取角色详情
     *
     * @param roleId 角色 id
     * @return {@link RoleDtoResult}
     */
    RoleDtoResult roleDetail(Long roleId);
    
    /**
     * 获取所有角色列表-分页查询
     *
     * @return {@link RoleDtoResult}
     */
    PageDtoResult<RoleDtoResult> getRolePage(PageDtoReq dtoReq);
    
    /**
     * 获取所有角色列表-列表查询
     *
     * @return {@link RoleDtoResult}
     */
    List<RoleDtoResult> getRoleList();
    
    /**
     * 通过角色 id 获取角色信息-列表查询
     *
     * @param roleIdList 角色 id
     * @return Role 集合
     */
    List<RoleDtoResult> getRoleList(List<Long> roleIdList);
    
    /**
     * 通过角色 id 获取角色信息-分页查询
     *
     * @param dtoReq 分页查询条件
     * @param roleIdList 角色 id
     */
    PageDtoResult<RoleDtoResult> getRolePage(PageDtoReq dtoReq, List<Long> roleIdList);
}
