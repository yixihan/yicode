package com.yixihan.yicode.user.openapi.api.rset.extra;

import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyUserInfoReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户资料 OpenApi
 *
 * @author yixihan
 * @date 2022/12/21 9:35
 */
@Api(tags = "用户资料 OpenApi")
@RequestMapping("/open/user/info")
public interface UserInfoOpenApi {
    
    @ApiOperation("更新用户资料")
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<UserInfoVO> modifyInfo (@RequestBody ModifyUserInfoReq req);
    
    @ApiOperation("获取用户资料")
    @GetMapping(value = "/detail", produces = "application/json")
    JsonResponse<UserInfoVO> getUserInfo (@RequestParam("userId") Long userId);
}
