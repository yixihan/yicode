package com.yixihan.yicode.user.api.rest.base;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.base.ModifyUserRoleDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户权限 api
 *
 * @author yixihan
 * @date 2022-10-22-15:32
 */
@Api(tags = "用户权限 api")
@RequestMapping("/user/role/")
public interface UserRoleApi {

    @ApiOperation ("添加用户角色")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addRole (@RequestBody ModifyUserRoleDtoReq dtoReq);

    @ApiOperation ("删除用户角色")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delRole (@RequestBody ModifyUserRoleDtoReq dtoReq);

    @ApiOperation ("获取用户的角色信息")
    @PostMapping("/roleList/{userId}")
    ApiResult<List<RoleDtoResult>> getUserRoleList(@PathVariable("userId") Long userId);

}
