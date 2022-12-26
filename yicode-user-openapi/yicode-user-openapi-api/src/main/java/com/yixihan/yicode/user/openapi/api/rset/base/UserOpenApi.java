package com.yixihan.yicode.user.openapi.api.rset.base;

import com.yixihan.yicode.common.enums.RoleEnums;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.common.valid.RoleAccess;
import com.yixihan.yicode.user.openapi.api.vo.request.base.*;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserDetailInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户 OpenApi
 *
 * @author yixihan
 * @date 2022-10-22-18:01
 */
@Api(tags = "用户 OpenApi")
@RequestMapping("/open/user/")
public interface UserOpenApi {

    @ApiOperation ("获取用户详细信息")
    @RoleAccess(value = RoleEnums.ADMIN)
    @GetMapping(value = "/detail", produces = "application/json")
    JsonResponse<UserDetailInfoVO> getUserInfo(@RequestParam("userId") Long userId);

    @ApiOperation ("获取当前登录用户详细信息")
    @GetMapping(value = "/now", produces = "application/json")
    JsonResponse<UserDetailInfoVO> getUserInfo();

    @ApiOperation ("用户注册")
    @PostMapping(value = "/register", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> register (@RequestBody RegisterUserReq req);

    @ApiOperation ("重置密码")
    @PostMapping(value = "/reset/password", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> resetPassword (@RequestBody ResetPasswordReq req);

    @ApiOperation ("绑定邮箱")
    @PostMapping(value = "/bind/email", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> bindEmail (@RequestBody EmailReq req);

    @ApiOperation ("解绑邮箱")
    @PostMapping(value = "/unbind/email", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> unbindEmail (@RequestBody EmailReq req);

    @ApiOperation ("绑定手机号")
    @PostMapping(value = "/bind/mobile", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> bindMobile (@RequestBody MobileReq req);

    @ApiOperation ("解绑手机号")
    @PostMapping(value = "/unbind/mobile", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> unbindMobile (@RequestBody MobileReq req);

    @ApiOperation ("修改用户名")
    @PostMapping(value = "/reset/username", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> resetUserName (@RequestBody ResetUserNameReq req);

    @ApiOperation ("用户注销")
    @PostMapping(value = "/cancellation", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> cancellation ();
}
