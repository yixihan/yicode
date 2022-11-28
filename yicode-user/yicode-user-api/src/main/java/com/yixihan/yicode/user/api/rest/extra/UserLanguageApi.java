package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLanguageDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLanguageDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户语言 api
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Api(tags = "用户语言 api")
@RequestMapping("/language/")
public interface UserLanguageApi {

    @ApiOperation("添加用户语言")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addUserLanguage (@RequestBody ModifyUserLanguageDtoReq dtoReq);

    @ApiOperation("修改用户语言")
    @PostMapping(value = "/modify", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> modifyUserLanguage (@RequestBody ModifyUserLanguageDtoReq dtoReq);

    @ApiOperation("获取用户语言列表")
    @PostMapping(value = "/get/{userId}", produces = "application/json")
    ApiResult<List<UserLanguageDtoResult>> getUserLanguage (@PathVariable("userId") Long userId);

}
