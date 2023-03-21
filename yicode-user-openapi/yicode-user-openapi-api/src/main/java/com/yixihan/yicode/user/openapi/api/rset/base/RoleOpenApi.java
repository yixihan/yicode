package com.yixihan.yicode.user.openapi.api.rset.base;

import com.yixihan.yicode.common.enums.user.RoleEnums;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.common.valid.RoleAccess;
import com.yixihan.yicode.user.openapi.api.vo.request.base.ModifyUserRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.UserRoleQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 角色 OpenApi
 *
 * @author yixihan
 * @date 2022/12/21 9:34
 */
@Api(tags = "角色 OpenApi")
@RoleAccess(value = {RoleEnums.ADMIN, RoleEnums.SUPER_ADMIN})
@RequestMapping("/open/role")
public interface RoleOpenApi {
    
    @ApiOperation ("用户添加角色")
    @RoleAccess(value = RoleEnums.SUPER_ADMIN)
    @PostMapping(value = "/user/add", produces = "application/json")
    JsonResponse<List<RoleVO>> addUserRole (@RequestBody ModifyUserRoleReq req);
    
    @ApiOperation ("用户删除角色")
    @RoleAccess(value = RoleEnums.SUPER_ADMIN)
    @DeleteMapping(value = "/user/del", produces = "application/json")
    JsonResponse<List<RoleVO>> delUserRole (@RequestBody ModifyUserRoleReq req);
    
    @ApiOperation ("获取角色列表")
    @PostMapping(value = "/role/detail", produces = "application/json")
    JsonResponse<PageVO<RoleVO>> getRolePage (@RequestBody PageReq req);
    
    @ApiOperation ("获取用户拥有角色列表")
    @PostMapping(value = "/user/role/detail", produces = "application/json")
    JsonResponse<PageVO<RoleVO>> getUserRolePage (@RequestBody UserRoleQueryReq req);
}
