package com.yixihan.yicode.user.api.rest.base;

import com.yixihan.yicode.common.enums.RoleEnums;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.common.valid.RoleAccess;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户 api
 *
 * @author yixihan
 * @date 2022-10-22-17:33
 */
@Api(tags = "用户 api")
@RequestMapping("/user")
public interface UserApi {

    @ApiOperation("获取用户详细信息")
    @RoleAccess(value = RoleEnums.USER)
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<UserDetailInfoDtoResult> getUserInfo(@RequestParam("userId") Long userId);

    @ApiOperation("通过 userId 获取用户信息")
    @PostMapping(value = "/info/id", produces = "application/json")
    ApiResult<UserDtoResult> getUserByUserId(@RequestParam("userId") Long userId);

    @ApiOperation("通过 userId 获取用户信息")
    @PostMapping(value = "/info/name", produces = "application/json")
    ApiResult<UserDtoResult> getUserByUserName(@RequestParam("userName") String userName);

    @ApiOperation("通过 mobile 获取用户信息")
    @PostMapping(value = "/info/mobile", produces = "application/json")
    ApiResult<UserDtoResult> getUserByMobile(@RequestParam("mobile") String mobile);

    @ApiOperation("通过 email 获取用户信息")
    @PostMapping(value = "/info/email", produces = "application/json")
    ApiResult<UserDtoResult> getUserByEmail(@RequestParam("email") String email);

    @ApiOperation("通过缓存获取用户信息")
    @PostMapping(value = "/info/token/", produces = "application/json")
    ApiResult<UserDtoResult> getUserByToken(@RequestParam("token") String token);

    @ApiOperation ("用户注册")
    @PostMapping(value = "/register", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> register (@RequestBody RegisterUserDtoReq dtoReq);

    @ApiOperation ("重置密码")
    @PostMapping(value = "/reset/password", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> resetPassword (@RequestBody ResetPasswordDtoReq dtoReq);

    @ApiOperation ("绑定邮箱")
    @PostMapping(value = "/bind/email", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> bindEmail (@RequestBody BindEmailDtoReq dtoReq);

    @ApiOperation ("解绑邮箱")
    @PostMapping(value = "/unbind/email", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> unbindEmail (@RequestParam("userId") Long userId);

    @ApiOperation ("绑定手机号")
    @PostMapping(value = "/bind/mobile", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> bindMobile (@RequestBody BindMobileDtoReq dtoReq);

    @ApiOperation ("解绑手机号")
    @PostMapping(value = "/unbind/mobile", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> unbindMobile (@RequestParam("userId") Long userId);

    @ApiOperation ("修改用户名")
    @PostMapping(value = "/reset/username", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> resetUserName (@RequestBody ResetUserNameDtoReq dtoReq);

    @ApiOperation ("用户注销")
    @PostMapping(value = "/cancellation", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> cancellation (@RequestParam("userId") Long userId);

    @ApiOperation ("校验用户名")
    @PostMapping(value = "/verify/username", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> verifyUserName (@RequestParam("userName") String userName);

    @ApiOperation ("校验邮箱")
    @PostMapping(value = "/verify/email", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> verifyUserEmail (@RequestParam("email")String email);

    @ApiOperation ("校验手机号")
    @PostMapping(value = "/verify/mobile", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> verifyUserMobile (@RequestParam("mobile")String mobile);

}
