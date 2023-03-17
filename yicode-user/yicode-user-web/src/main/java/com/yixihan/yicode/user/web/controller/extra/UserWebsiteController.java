package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserWebsiteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserWebsiteDtoResult;
import com.yixihan.yicode.user.api.rest.extra.UserWebsiteApi;
import com.yixihan.yicode.user.biz.service.extra.UserWebsiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户个人网站表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Slf4j
@RestController
public class UserWebsiteController implements UserWebsiteApi {

    @Resource
    private UserWebsiteService service;

    @Override
    public void addUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        service.addUserWebsite (dtoReq);
    }

    @Override
    public void delUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        service.delUserWebsite (dtoReq);
    }

    @Override
    public ApiResult<List<UserWebsiteDtoResult>> getUserWebsite(Long userId) {
        return ApiResult.create (service.getUserWebsite (userId));
    }
}
