package com.yixihan.yicode.user.web.controller.base;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.api.rest.base.UserApi;
import com.yixihan.yicode.user.biz.service.base.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Slf4j
@RestController
public class UserController implements UserApi {

    @Resource
    private UserService userService;

    @Override
    public ApiResult<UserDetailInfoDtoResult> getUserInfo(Long userId) {
        return ApiResult.create (userService.getUserInfo (userId));
    }

    @Override
    public ApiResult<UserDtoResult> getUserByUserId(Long userId) {
        return ApiResult.create (userService.getUserById (userId));
    }

    @Override
    public ApiResult<UserDtoResult> getUserByUserName(String userName) {
        return ApiResult.create (userService.getUserByUserName (userName));
    }

    @Override
    public ApiResult<UserDtoResult> getUserByMobile(String mobile) {
        return ApiResult.create (userService.getUserByMobile (mobile));
    }

    @Override
    public ApiResult<UserDtoResult> getUserByEmail(String email) {
        return ApiResult.create (userService.getUserByEmail (email));
    }

    @Override
    public ApiResult<UserDtoResult> getUserByToken(String token) {
        return ApiResult.create (userService.getUserByToken (token));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> register(RegisterUserDtoReq dtoReq) {
        return ApiResult.create (userService.register (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> resetPassword(ResetPasswordDtoReq dtoReq) {
        return ApiResult.create (userService.resetPassword (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> bindEmail(BindEmailDtoReq dtoReq) {
        return ApiResult.create (userService.bindEmail (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> unbindEmail(Long userId) {
        return ApiResult.create (userService.unbindEmail (userId));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> bindMobile(BindMobileDtoReq dtoReq) {
        return ApiResult.create (userService.bindMobile (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> unbindMobile(Long userId) {
        return ApiResult.create (userService.unbindMobile (userId));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> resetUserName(ResetUserNameDtoReq dtoReq) {
        return ApiResult.create (userService.resetUserName (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> cancellation(Long userId) {
        return ApiResult.create (userService.cancellation (userId));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> verifyUserName(String userName) {
        return ApiResult.create (userService.verifyUserName (userName));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> verifyUserEmail(String email) {
        return ApiResult.create (userService.verifyUserEmail (email));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> verifyUserMobile(String mobile) {
        return ApiResult.create (userService.verifyUserMobile (mobile));
    }
}
