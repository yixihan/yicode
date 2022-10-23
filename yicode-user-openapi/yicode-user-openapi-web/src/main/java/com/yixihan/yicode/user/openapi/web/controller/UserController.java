package com.yixihan.yicode.user.openapi.web.controller;

import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.UserOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.response.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.biz.server.UserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yixihan
 * @date 2022-10-22-18:05
 */
@RestController
public class UserController implements UserOpenApi {

    @Resource
    private UserService userService;

    @Override
    public JsonResponse<UserDetailInfoVO> getUserInfo(Long userId) {
        return JsonResponse.ok (userService.getUserInfo (userId));
    }
}