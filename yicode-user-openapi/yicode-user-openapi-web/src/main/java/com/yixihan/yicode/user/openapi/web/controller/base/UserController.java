package com.yixihan.yicode.user.openapi.web.controller.base;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.base.UserOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.base.*;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private UserService service;

    @Override
    public JsonResponse<UserDetailInfoVO> getUserDetailInfo(Long userId) {
        return JsonResponse.ok (service.getUserDetailInfo (userId));
    }

    @Override
    public JsonResponse<UserDetailInfoVO> getUserDetailInfo() {
        return JsonResponse.ok (service.getUserDetailInfo ());
    }
    
    @Override
    public JsonResponse<PageVO<UserDetailInfoVO>> getUserList(QueryUserReq req) {
        return JsonResponse.ok (service.getUserList (req));
    }
    
    @Override
    public void register(RegisterUserReq req) {
        service.register (req);
    }

    @Override
    public void resetPassword(ResetPasswordReq req) {
        service.resetPassword (req);
    }

    @Override
    public void bindEmail(EmailReq req) {
        service.bindEmail (req);
    }

    @Override
    public void unbindEmail(EmailReq req) {
        service.unbindEmail (req);
    }

    @Override
    public void bindMobile(MobileReq req) {
        service.bindMobile (req);
    }

    @Override
    public void unbindMobile(MobileReq req) {
        service.unbindMobile (req);
    }

    @Override
    public void resetUserName(ResetUserNameReq req) {
        service.resetUserName(req);
    }

    @Override
    public void cancellation(Long userId) {
        service.cancellation (userId);
    }
}
