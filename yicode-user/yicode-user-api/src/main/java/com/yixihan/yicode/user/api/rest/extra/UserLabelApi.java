package com.yixihan.yicode.user.api.rest.extra;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyUserLabelDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.UserLabelDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户标签 api
 *
 * @author yixihan
 * @date 2022/12/21 16:03
 */
@Api(tags = "用户标签 api")
@RequestMapping("/user/label/")
public interface UserLabelApi {
    
    @ApiOperation("添加用户标签")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addUserLabel (@RequestBody ModifyUserLabelDtoReq dtoReq);
    
    @ApiOperation("获取用户标签列表")
    @PostMapping(value = "/detail/{userId}", produces = "application/json")
    ApiResult<List<UserLabelDtoResult>> getUserLabel (@PathVariable("userId") Long userId);
}
