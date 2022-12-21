package com.yixihan.yicode.user.openapi.biz.service.base.impl;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.user.openapi.api.vo.request.base.AddRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.AddUserRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;
import com.yixihan.yicode.user.openapi.biz.feign.user.base.RoleFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.base.UserRoleFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.RoleService;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色服务实现类
 *
 * @author yixihan
 * @date 2022/12/21 9:43
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    
    @Resource
    private RoleFeignClient roleFeignClient;
    
    @Resource
    private UserRoleFeignClient userRoleFeignClient;
    
    @Resource
    private UserService userService;
    
    @Override
    public CommonVO<Boolean> addRole(AddRoleReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> delRole(Long roleId) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> addUserRole(AddUserRoleReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> delUserRole(Long roleId) {
        return null;
    }
    
    @Override
    public PageVO<RoleVO> getRoleList() {
        return null;
    }
    
    @Override
    public PageVO<RoleVO> getUserRoleList(Long userId) {
        return null;
    }
}
