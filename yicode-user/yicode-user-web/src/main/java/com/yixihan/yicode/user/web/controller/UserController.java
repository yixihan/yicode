package com.yixihan.yicode.user.web.controller;


import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
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
}
