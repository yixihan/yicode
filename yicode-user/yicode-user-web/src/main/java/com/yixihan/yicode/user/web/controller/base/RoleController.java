package com.yixihan.yicode.user.web.controller.base;


import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.api.rest.base.RoleApi;
import com.yixihan.yicode.user.biz.service.base.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Slf4j
@RestController
public class RoleController implements RoleApi {
    
    @Resource
    private RoleService service;
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> addRole(String roleName) {
        return ApiResult.create (service.addRole (roleName));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> delRole(Long roleId) {
        return ApiResult.create (service.delRole (roleId));
    }
    
    @Override
    public ApiResult<PageDtoResult<RoleDtoResult>> getRoleList(PageDtoReq dtoReq) {
        return ApiResult.create (service.getRoleList (dtoReq));
    }
}
