package com.yixihan.yicode.user.openapi.biz.service.base;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.user.openapi.api.vo.request.base.ModifyUserRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.UserRoleQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;

import java.util.List;

/**
 * 角色 服务类
 *
 * @author yixihan
 * @date 2022/12/21 9:43
 */
public interface RoleService {
    
    /**
     * 给用户添加角色
     *
     * @param req 请求参数
     * @return {@link RoleVO}
     */
    List<RoleVO> addUserRole(ModifyUserRoleReq req);
    
    /**
     * 删除用户拥有角色
     *
     * @param req 请求参数
     * @return {@link RoleVO}
     */
    List<RoleVO> delUserRole(ModifyUserRoleReq req);
    
    /**
     * 获取角色列表
     *
     * @param req 请求参数
     * @return {@link RoleVO}
     */
    PageVO<RoleVO> getRolePage(PageReq req);
    
    /**
     * 获取用户拥有角色列表
     *
     * @param req 请求参数
     * @return {@link RoleVO}
     */
    PageVO<RoleVO> getUserRolePage(UserRoleQueryReq req);
}
