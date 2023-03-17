package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLanguageDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import com.yixihan.yicode.user.api.rest.extra.UserLanguageApi;
import com.yixihan.yicode.user.biz.service.extra.UserLanguageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户语言表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Slf4j
@RestController
public class UserLanguageController implements UserLanguageApi {

    @Resource
    private UserLanguageService service;

    @Override
    public ApiResult<List<UserLanguageDtoResult>> addUserLanguage(ModifyUserLanguageDtoReq dtoReq) {
        return ApiResult.create (service.addUserLanguage (dtoReq));
    }

    @Override
    public ApiResult<List<UserLanguageDtoResult>> modifyUserLanguage(ModifyUserLanguageDtoReq dtoReq) {
        return ApiResult.create (service.modifyUserLanguage (dtoReq));
    }

    @Override
    public ApiResult<List<UserLanguageDtoResult>> getUserLanguage(Long userId) {
        return ApiResult.create (service.getUserLanguage (userId));
    }
}
