package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserWebsiteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserWebsiteDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户个人网站 api
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Api(tags = "用户个人网站 api")
@RequestMapping("/website")
public interface UserWebsiteApi {

    @ApiOperation("添加用户个人网站")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addUserWebsite (@RequestBody ModifyUserWebsiteDtoReq dtoReq);
    
    @ApiOperation("删除用户个人网站")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delUserWebsite (@RequestBody ModifyUserWebsiteDtoReq dtoReq);

    @ApiOperation("获取用户个人网站列表")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<List<UserWebsiteDtoResult>> getUserWebsite (@RequestParam("userId") Long userId);

}
