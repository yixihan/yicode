package com.yixihan.yicode.user.openapi.web.controller.extra;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.extra.UserInfoOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyUserInfoReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserInfoVO;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户资料 前端控制器
 *
 * @author yixihan
 * @date 2022/12/21 9:39
 */
@Slf4j
@RestController
public class UserInfoController implements UserInfoOpenApi {
    
    @Resource
    private UserInfoService service;
    
    @Override
    public JsonResponse<CommonVO<Boolean>> modifyInfo(ModifyUserInfoReq req) {
        return JsonResponse.ok (service.modifyInfo (req));
    }
    
    @Override
    public JsonResponse<UserInfoVO> getUserInfo(Long userId) {
        return JsonResponse.ok (service.getUserInfo (userId));
    }
}
