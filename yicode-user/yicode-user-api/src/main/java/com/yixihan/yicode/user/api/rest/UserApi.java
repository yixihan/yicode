package com.yixihan.yicode.user.api.rest;

import com.yixihan.yicode.common.enums.RoleEnum;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.common.valid.RoleAccess;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.UserDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户 api
 *
 * @author yixihan
 * @date 2022-10-22-17:33
 */
@Api(tags = "用户 api")
@RequestMapping("/user/")
public interface UserApi {

    @ApiOperation("获取用户详细信息")
    @RoleAccess(value = RoleEnum.USER)
    @PostMapping(value = "/userdetailinfo", produces = "application/json")
    ApiResult<UserDetailInfoDtoResult> getUserInfo(@RequestParam("userId") Long userId);

    @ApiOperation ("通过 userId 获取用户信息")
    @PostMapping(value = "/userbyid", produces = "application/json")
    ApiResult<UserDtoResult> getUserByUserId(@RequestParam("userId") Long userId);

    @ApiOperation ("通过 userId 获取用户信息")
    @PostMapping(value = "/userbyname", produces = "application/json")
    ApiResult<UserDtoResult> getUserByUserName(@RequestParam("userName") String userName);

    @ApiOperation ("通过 mobile 获取用户信息")
    @PostMapping(value = "/userbymobile", produces = "application/json")
    ApiResult<UserDtoResult> getUserByMobile(@RequestParam("mobile") String mobile);

    @ApiOperation ("通过 email 获取用户信息")
    @PostMapping(value = "/userbyemail", produces = "application/json")
    ApiResult<UserDtoResult> getUserByEmail(@RequestParam("email") String email);

}
