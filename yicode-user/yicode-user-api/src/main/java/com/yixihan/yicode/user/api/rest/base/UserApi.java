package com.yixihan.yicode.user.api.rest.base;

import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户 api
 *
 * @author yixihan
 * @date 2022-10-22-17:33
 */
@Api(tags = "用户 api")
@RequestMapping("/user")
public interface UserApi {
    
    @ApiOperation("通过 userId 获取用户信息")
    @PostMapping(value = "/info/id", produces = "application/json")
    ApiResult<UserDtoResult> getUserByUserId(@RequestBody Long userId);
    
    @ApiOperation("通过 userName 获取用户信息")
    @PostMapping(value = "/info/name", produces = "application/json")
    ApiResult<UserDtoResult> getUserByUserName(@RequestBody String userName);
    
    @ApiOperation("通过 mobile 获取用户信息")
    @PostMapping(value = "/info/mobile", produces = "application/json")
    ApiResult<UserDtoResult> getUserByMobile(@RequestBody String mobile);
    
    @ApiOperation("通过 email 获取用户信息")
    @PostMapping(value = "/info/email", produces = "application/json")
    ApiResult<UserDtoResult> getUserByEmail(@RequestBody String email);
    
    @ApiOperation("通过 cache 获取用户信息")
    @PostMapping(value = "/info/token", produces = "application/json")
    ApiResult<UserDtoResult> getUserByToken(@RequestBody String token);
    
    @ApiOperation("通过 cache 获取用户信息")
    @PostMapping(value = "/id/token", produces = "application/json")
    ApiResult<Long> getUserIdByToken(@RequestBody String token);
    
    
    @ApiOperation("通过 userId 获取用户信息-列表查询")
    @PostMapping(value = "/list/info/id", produces = "application/json")
    ApiResult<List<UserDtoResult>> getUserListByUserId(@RequestBody List<Long> userIdList);
    
    @ApiOperation("通过 userId 获取用户常用信息-列表查询")
    @PostMapping(value = "/list/common/id", produces = "application/json")
    ApiResult<List<UserCommonDtoResult>> getUserCommonInfo (@RequestBody List<Long> userIdList);
    
    @ApiOperation ("获取用户 id 列表-分页查询")
    @PostMapping(value = "/detail/page", produces = "application/json")
    ApiResult<PageDtoResult<Long>> getUserList(@RequestBody QueryUserDtoReq dtoReq);

    @ApiOperation ("用户注册")
    @PostMapping(value = "/register", produces = "application/json")
    void register (@RequestBody RegisterUserDtoReq dtoReq);

    @ApiOperation ("重置密码")
    @PostMapping(value = "/reset/password", produces = "application/json")
    void resetPassword (@RequestBody ResetPasswordDtoReq dtoReq);

    @ApiOperation ("绑定邮箱")
    @PostMapping(value = "/bind/email", produces = "application/json")
    void bindEmail (@RequestBody BindEmailDtoReq dtoReq);

    @ApiOperation ("解绑邮箱")
    @PostMapping(value = "/unbind/email", produces = "application/json")
    void unbindEmail (@RequestBody Long userId);

    @ApiOperation ("绑定手机号")
    @PostMapping(value = "/bind/mobile", produces = "application/json")
    void bindMobile (@RequestBody BindMobileDtoReq dtoReq);

    @ApiOperation ("解绑手机号")
    @PostMapping(value = "/unbind/mobile", produces = "application/json")
    void unbindMobile (@RequestBody Long userId);

    @ApiOperation ("修改用户名")
    @PostMapping(value = "/reset/username", produces = "application/json")
    void resetUserName (@RequestBody ResetUserNameDtoReq dtoReq);

    @ApiOperation ("用户注销")
    @PostMapping(value = "/cancellation", produces = "application/json")
    void cancellation (@RequestBody Long userId);

    @ApiOperation ("校验用户名")
    @PostMapping(value = "/verify/username", produces = "application/json")
    ApiResult<Boolean> verifyUserName (@RequestBody String userName);

    @ApiOperation ("校验邮箱")
    @PostMapping(value = "/verify/email", produces = "application/json")
    ApiResult<Boolean> verifyUserEmail (@RequestBody String email);

    @ApiOperation ("校验手机号")
    @PostMapping(value = "/verify/mobile", produces = "application/json")
    ApiResult<Boolean> verifyUserMobile (@RequestBody String mobile);
}
