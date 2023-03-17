package com.yixihan.yicode.user.api.rest.base;

import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.base.ModifyUserRoleDtoReq;
import com.yixihan.yicode.user.api.dto.request.base.UserRoleQueryDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户权限 api
 *
 * @author yixihan
 * @date 2022-10-22-15:32
 */
@Api(tags = "用户权限 api")
@RequestMapping("/user/role")
public interface UserRoleApi {

    @ApiOperation ("添加用户角色")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<List<RoleDtoResult>> addRole (@RequestBody ModifyUserRoleDtoReq dtoReq);

    @ApiOperation ("删除用户角色")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<List<RoleDtoResult>> delRole (@RequestBody ModifyUserRoleDtoReq dtoReq);
    
    @ApiOperation ("获取用户的角色信息-列表查询")
    @PostMapping(value = "/detail/list", produces = "application/json")
    ApiResult<List<RoleDtoResult>> getUserRoleList(@RequestBody Long userId);

    @ApiOperation ("获取用户的角色信息-分页查询")
    @PostMapping(value = "/detail/page", produces = "application/json")
    ApiResult<PageDtoResult<RoleDtoResult>> getUserRolePage(@RequestBody UserRoleQueryDtoReq dtoReq);

}
