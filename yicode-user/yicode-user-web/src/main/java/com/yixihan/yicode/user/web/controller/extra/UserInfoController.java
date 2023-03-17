package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.api.rest.extra.UserInfoApi;
import com.yixihan.yicode.user.biz.service.extra.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    private UserInfoService service;

    @Override
    public void modifyInfo(ModifyUserInfoDtoReq dtoReq) {
        service.modifyInfo (dtoReq);
    }

    @Override
    public ApiResult<UserInfoDtoResult> getUserInfo(Long userId) {
        return ApiResult.create (service.getUserInfo (userId));
    }
    
    @Override
    public ApiResult<List<UserInfoDtoResult>> getUserInfoList(List<Long> userIdList) {
        return ApiResult.create (service.getUserInfoList (userIdList));
    }
}
