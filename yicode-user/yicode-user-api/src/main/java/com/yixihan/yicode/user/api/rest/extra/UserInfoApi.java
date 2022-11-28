package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserInfoDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户资料 api
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Api(tags = "用户资料 api")
@RequestMapping("/info/")
public interface UserInfoApi {

    @ApiOperation("更新用户资料")
    @PostMapping("/modify")
    ApiResult<CommonDtoResult<Boolean>> modifyInfo (@RequestBody ModifyUserInfoDtoReq dtoReq);

    @ApiOperation("获取用户资料")
    @PostMapping("/get/{userId}")
    ApiResult<UserInfoDtoResult> getUserInfo (@PathVariable("userId") Long userId);
}
