package com.yixihan.yicode.user.web.controller;


import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.RoleDtoResult;
import com.yixihan.yicode.user.api.rest.UserRoleApi;
import com.yixihan.yicode.user.biz.service.UserRoleService;
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
@RestController
public class UserRoleController implements UserRoleApi {

    @Resource
    private UserRoleService userRoleService;

    @Override
    public ApiResult<List<RoleDtoResult>> getUserRoleList(Long userId) {
        return ApiResult.create (userRoleService.getUserRoleByUserId (userId));
    }
}
