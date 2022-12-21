package com.yixihan.yicode.user.openapi.biz.service.base;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.user.openapi.api.vo.request.base.AddRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.AddUserRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;

/**
 * 角色 服务类
 *
 * @author yixihan
 * @date 2022/12/21 9:43
 */
public interface RoleService {
    
    /**
     * 添加角色
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> addRole(AddRoleReq req);
    
    /**
     * 删除用户
     *
     * @param roleId 角色 ID
     */
    CommonVO<Boolean> delRole(Long roleId);
    
    /**
     * 给用户添加角色
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> addUserRole(AddUserRoleReq req);
    
    /**
     * 删除用户拥有角色
     *
     * @param roleId 角色 ID
     */
    CommonVO<Boolean> delUserRole(Long roleId);
    
    /**
     * 获取角色列表
     */
    PageVO<RoleVO> getRoleList();
    
    /**
     * 获取用户拥有角色列表
     *
     * @param userId 用户 ID
     */
    PageVO<RoleVO> getUserRoleList(Long userId);
}
