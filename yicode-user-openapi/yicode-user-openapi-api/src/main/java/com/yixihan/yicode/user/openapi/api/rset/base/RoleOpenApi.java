package com.yixihan.yicode.user.openapi.api.rset.base;

import com.yixihan.yicode.common.enums.RoleEnums;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.common.valid.RoleAccess;
import com.yixihan.yicode.user.openapi.api.vo.request.base.AddRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.AddUserRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.UserRoleQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 角色 OpenApi
 *
 * @author yixihan
 * @date 2022/12/21 9:34
 */
@Api(tags = "角色 OpenApi")
@RequestMapping("/open/role")
public interface RoleOpenApi {
    
    @ApiOperation ("添加角色")
    @RoleAccess(value = RoleEnums.ADMIN)
    @PostMapping(value = "/role/add", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> addRole (@RequestBody AddRoleReq req);
    
    @ApiOperation ("删除角色")
    @RoleAccess(value = RoleEnums.ADMIN)
    @DeleteMapping(value = "/role/del", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> delRole (@RequestParam("roleId") Long roleId);
    
    @ApiOperation ("用户添加角色")
    @RoleAccess(value = RoleEnums.ADMIN)
    @PostMapping(value = "/user/add", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> addUserRole (@RequestBody AddUserRoleReq req);
    
    @ApiOperation ("用户删除角色")
    @RoleAccess(value = RoleEnums.ADMIN)
    @DeleteMapping(value = "/user/del", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> delUserRole (@RequestParam("roleId") Long roleId);
    
    @ApiOperation ("获取角色列表")
    @RoleAccess(value = RoleEnums.ADMIN)
    @PostMapping(value = "/role/detail", produces = "application/json")
    JsonResponse<PageVO<RoleVO>> getRolePage (@RequestBody PageReq req);
    
    @ApiOperation ("获取用户拥有角色列表")
    @RoleAccess(value = RoleEnums.ADMIN)
    @PostMapping(value = "/user/role/detail", produces = "application/json")
    JsonResponse<PageVO<RoleVO>> getUserRolePage (@RequestBody UserRoleQueryReq req);
}
