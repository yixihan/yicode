package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
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
    private UserWebsiteService userWebsiteService;

    @Override
    public ApiResult<CommonDtoResult<Boolean>> addUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        return ApiResult.create (userWebsiteService.addUserWebsite (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> delUserWebsite(ModifyUserWebsiteDtoReq dtoReq) {
        return ApiResult.create (userWebsiteService.delUserWebsite (dtoReq));
    }

    @Override
    public ApiResult<List<UserWebsiteDtoResult>> getUserWebsite(Long userId) {
        return ApiResult.create (userWebsiteService.getUserWebsite (userId));
    }
}
