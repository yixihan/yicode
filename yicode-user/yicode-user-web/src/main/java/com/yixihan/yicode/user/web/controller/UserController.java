package com.yixihan.yicode.user.web.controller;


import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.UserDtoResult;
import com.yixihan.yicode.user.api.rest.UserApi;
import com.yixihan.yicode.user.biz.service.UserService;
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
}
