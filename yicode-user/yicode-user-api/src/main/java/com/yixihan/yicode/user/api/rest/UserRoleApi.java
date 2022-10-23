package com.yixihan.yicode.user.api.rest;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.RoleDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户模块 - 用户权限
 *
 * @author yixihan
 * @date 2022-10-22-15:32
 */
@Api(tags = "用户模块 - 用户权限")
@RequestMapping("/user/role")
public interface UserRoleApi {

    @ApiOperation ("获取用户的角色信息")
    @PostMapping("/roleList")
    ApiResult<List<RoleDtoResult>> getUserRoleList(@RequestParam("userId") Long userId);

}
