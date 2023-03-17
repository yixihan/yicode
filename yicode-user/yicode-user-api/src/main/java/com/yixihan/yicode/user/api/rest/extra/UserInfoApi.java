package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户资料 api
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Api(tags = "用户资料 api")
@RequestMapping("/info")
public interface UserInfoApi {

    @ApiOperation("更新用户资料")
    @PostMapping(value = "/modify", produces = "application/json")
    void modifyInfo (@RequestBody ModifyUserInfoDtoReq dtoReq);

    @ApiOperation("获取用户资料")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<UserInfoDtoResult> getUserInfo (@RequestBody Long userId);
    
    @ApiOperation("获取用户资料")
    @PostMapping(value = "/list/detail", produces = "application/json")
    ApiResult<List<UserInfoDtoResult>> getUserInfoList(@RequestBody List<Long> userIdList);
}
