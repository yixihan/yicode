package com.yixihan.yicode.user.api.rest;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yixihan
 * @date 2022-10-22-17:33
 */
@Api(tags = "用户信息 - Api")
@RequestMapping("/user/")
public interface UserApi {

    @ApiOperation("获取用户详细信息")
    @PostMapping(value = "/userinfo", produces = "application/json")
    ApiResult<UserDetailInfoDtoResult> getUserInfo(@RequestParam("userId") Long userId);

}
