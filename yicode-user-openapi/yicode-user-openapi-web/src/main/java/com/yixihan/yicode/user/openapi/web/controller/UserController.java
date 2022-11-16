package com.yixihan.yicode.user.openapi.web.controller;

import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.UserOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.RegisterUserReq;
import com.yixihan.yicode.user.openapi.api.vo.request.ResetPasswordReq;
import com.yixihan.yicode.user.openapi.api.vo.response.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.biz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户 前端控制器
 *
 * @author yixihan
 * @date 2022-10-22-18:05
 */
@Slf4j
@RestController
public class UserController implements UserOpenApi {

    @Resource
    private UserService userService;

    @Override
    public JsonResponse<UserDetailInfoVO> getUserInfo(Long userId) {
        return JsonResponse.ok (userService.getUserInfo (userId));
    }

    @Override
    public JsonResponse<UserDetailInfoVO> getUserInfo(HttpServletRequest request) {
        return JsonResponse.ok (userService.getUserInfo (request));
    }

    @Override
    public JsonResponse<Boolean> register(RegisterUserReq req) {
        return null;
    }

    @Override
    public JsonResponse<Boolean> resetPassword(ResetPasswordReq req) {
        return null;
    }
}
