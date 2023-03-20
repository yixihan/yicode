package com.yixihan.yicode.user.web.controller.base;


import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.api.rest.base.UserApi;
import com.yixihan.yicode.user.biz.service.base.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    private UserService service;

    @Override
    public ApiResult<UserDtoResult> getUserByUserId(Long userId) {
        return ApiResult.create (service.getUserById (userId));
    }
    
    @Override
    public ApiResult<List<UserDtoResult>> getUserListByUserId(List<Long> userIdList) {
        return ApiResult.create (service.getUserListByUserId (userIdList));
    }
    
    @Override
    public ApiResult<List<UserCommonDtoResult>> getUserCommonInfo(List<Long> userIdList) {
        return ApiResult.create (service.getUserCommonInfo (userIdList));
    }
    
    @Override
    public ApiResult<UserDtoResult> getUserByUserName(String userName) {
        return ApiResult.create (service.getUserByUserName (userName));
    }

    @Override
    public ApiResult<UserDtoResult> getUserByMobile(String mobile) {
        return ApiResult.create (service.getUserByMobile (mobile));
    }

    @Override
    public ApiResult<UserDtoResult> getUserByEmail(String email) {
        return ApiResult.create (service.getUserByEmail (email));
    }

    @Override
    public ApiResult<UserDtoResult> getUserByToken(String token) {
        return ApiResult.create (service.getUserByToken (token));
    }
    
    @Override
    public ApiResult<Long> getUserIdByToken(String token) {
        return ApiResult.create (service.getUserIdByToken (token));
    }
    
    @Override
    public ApiResult<PageDtoResult<Long>> getUserList(PageDtoReq dtoReq) {
        return ApiResult.create (service.getUserList (dtoReq));
    }
    
    @Override
    public void register(RegisterUserDtoReq dtoReq) {
        service.register (dtoReq);
    }

    @Override
    public void resetPassword(ResetPasswordDtoReq dtoReq) {
        service.resetPassword (dtoReq);
    }

    @Override
    public void bindEmail(BindEmailDtoReq dtoReq) {
        service.bindEmail (dtoReq);
    }

    @Override
    public void unbindEmail(Long userId) {
        service.unbindEmail (userId);
    }

    @Override
    public void bindMobile(BindMobileDtoReq dtoReq) {
        service.bindMobile (dtoReq);
    }

    @Override
    public void unbindMobile(Long userId) {
        service.unbindMobile (userId);
    }

    @Override
    public void resetUserName(ResetUserNameDtoReq dtoReq) {
        service.resetUserName (dtoReq);
    }

    @Override
    public void cancellation(Long userId) {
        service.cancellation (userId);
    }

    @Override
    public ApiResult<Boolean> verifyUserName(String userName) {
        return ApiResult.create (service.verifyUserName (userName));
    }

    @Override
    public ApiResult<Boolean> verifyUserEmail(String email) {
        return ApiResult.create (service.verifyUserEmail (email));
    }

    @Override
    public ApiResult<Boolean> verifyUserMobile(String mobile) {
        return ApiResult.create (service.verifyUserMobile (mobile));
    }
}
