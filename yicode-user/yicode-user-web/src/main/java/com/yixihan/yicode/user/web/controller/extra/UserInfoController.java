package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.api.rest.extra.UserInfoApi;
import com.yixihan.yicode.user.biz.service.extra.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Slf4j
@RestController
public class UserInfoController implements UserInfoApi {

    @Resource
    private UserInfoService userInfoService;

    @Override
    public ApiResult<CommonDtoResult<Boolean>> modifyInfo(ModifyUserInfoDtoReq dtoReq) {
        return ApiResult.create (userInfoService.modifyInfo (dtoReq));
    }

    @Override
    public ApiResult<UserInfoDtoResult> getUserInfo(Long userId) {
        return ApiResult.create (userInfoService.getUserInfo (userId));
    }
}
