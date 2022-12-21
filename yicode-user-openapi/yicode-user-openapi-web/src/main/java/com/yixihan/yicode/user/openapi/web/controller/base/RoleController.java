package com.yixihan.yicode.user.openapi.web.controller.base;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.base.RoleOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.base.AddRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.AddUserRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;
import com.yixihan.yicode.user.openapi.biz.service.base.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色 前端控制器
 *
 * @author yixihan
 * @date 2022/12/21 9:41
 */
@Slf4j
@RestController
public class RoleController implements RoleOpenApi {
    
    @Resource
    private RoleService service;
    
    @Override
    public JsonResponse<CommonVO<Boolean>> addRole(AddRoleReq req) {
        return JsonResponse.ok (service.addRole (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delRole(Long roleId) {
        return JsonResponse.ok (service.delRole (roleId));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> addUserRole(AddUserRoleReq req) {
        return JsonResponse.ok (service.addUserRole (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delUserRole(Long roleId) {
        return JsonResponse.ok (service.delUserRole (roleId));
    }
    
    @Override
    public JsonResponse<PageVO<RoleVO>> getRoleList() {
        return JsonResponse.ok (service.getRoleList ());
    }
    
    @Override
    public JsonResponse<PageVO<RoleVO>> getUserRoleList(Long userId) {
        return JsonResponse.ok (service.getUserRoleList (userId));
    }
}
