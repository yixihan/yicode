package com.yixihan.yicode.user.web.controller.base;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.api.rest.base.UserRoleApi;
import com.yixihan.yicode.user.biz.service.base.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户角色对应表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Slf4j
@RestController
public class UserRoleController implements UserRoleApi {

    @Resource
    private UserRoleService userRoleService;

    @Override
    public ApiResult<CommonDtoResult<Boolean>> addRole(Long userId, String role) {
        return ApiResult.create (userRoleService.addRole(userId, role));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> delRole(Long userId, String role) {
        return ApiResult.create (userRoleService.delRole (userId, role));
    }

    @Override
    public ApiResult<List<RoleDtoResult>> getUserRoleList(Long userId) {
        return ApiResult.create (userRoleService.getUserRoleByUserId (userId));
    }
}
