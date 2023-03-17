package com.yixihan.yicode.user.web.controller.base;


import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.api.rest.base.RoleApi;
import com.yixihan.yicode.user.biz.service.base.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public ApiResult<RoleDtoResult> addRole(String roleName) {
        return ApiResult.create (service.addRole (roleName));
    }
    
    @Override
    public void delRole(Long roleId) {
        service.delRole (roleId);
    }
    
    @Override
    public ApiResult<Boolean> validateRole(Long roleId) {
        return ApiResult.create (service.validateRole (roleId));
    }
    
    @Override
    public ApiResult<RoleDtoResult> roleDetail(Long roleId) {
        return ApiResult.create (service.roleDetail (roleId));
    }
    
    @Override
    public ApiResult<PageDtoResult<RoleDtoResult>> rolePageDetail(PageDtoReq dtoReq) {
        return ApiResult.create (service.getRolePage (dtoReq));
    }
    
    @Override
    public ApiResult<List<RoleDtoResult>> roleListDetail() {
        return ApiResult.create (service.getRoleList ());
    }
}
