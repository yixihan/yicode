package com.yixihan.yicode.user.api.rest.base;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ApiOperation ("添加角色")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addRole (@RequestParam("userId") Long userId, @RequestParam("role") String role);

    @ApiOperation ("删除角色")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delRole (@RequestParam("userId") Long userId, @RequestParam("role") String role);

    @ApiOperation ("获取用户的角色信息")
    @PostMapping("/roleList")
    ApiResult<List<RoleDtoResult>> getUserRoleList(@RequestParam("userId") Long userId);

}
