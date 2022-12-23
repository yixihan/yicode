package com.yixihan.yicode.user.openapi.api.rset.extra;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyUserInfoReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户资料 OpenApi
 *
 * @author yixihan
 * @date 2022/12/21 9:35
 */
@Api(tags = "用户资料 OpenApi")
@RequestMapping("/open/user/info/")
public interface UserInfoOpenApi {
    
    @ApiOperation("更新用户资料")
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> modifyInfo (@RequestBody ModifyUserInfoReq req);
    
    @ApiOperation("获取用户资料")
    @PostMapping(value = "/detail/{userId}", produces = "application/json")
    JsonResponse<UserInfoVO> getUserInfo (@PathVariable("userId") Long userId);
}
