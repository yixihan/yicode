package com.yixihan.yicode.user.openapi.web.controller;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.UserOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.*;
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
    public JsonResponse<CommonVO<Boolean>> register(RegisterUserReq req) {
        return JsonResponse.ok (userService.register (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> resetPassword(ResetPasswordReq req) {
        return JsonResponse.ok (userService.resetPassword (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> bindEmail(HttpServletRequest request, BindEmailReq req) {
        return JsonResponse.ok (userService.bindEmail (request, req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> unbindEmail(HttpServletRequest request) {
        return JsonResponse.ok (userService.unbindEmail (request));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> bindMobile(HttpServletRequest request, BindMobileReq req) {
        return JsonResponse.ok (userService.bindMobile (request, req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> unbindMobile(HttpServletRequest request) {
        return JsonResponse.ok (userService.unbindMobile (request));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> resetUserName(HttpServletRequest request, ResetUserNameReq req) {
        return JsonResponse.ok (userService.resetUserName(request, req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> cancellation(HttpServletRequest request) {
        return JsonResponse.ok (userService.cancellation (request));
    }
}
