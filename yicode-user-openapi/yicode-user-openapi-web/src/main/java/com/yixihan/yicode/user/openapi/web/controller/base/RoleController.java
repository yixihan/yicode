package com.yixihan.yicode.user.openapi.web.controller.base;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.rset.base.RoleOpenApi;
import com.yixihan.yicode.user.openapi.api.vo.request.base.ModifyUserRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.UserRoleQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;
import com.yixihan.yicode.user.openapi.biz.service.base.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色 前端控制器
 *
 * @author yixihan
 * @date 2022/12/21 9:41
 */
@Slf4j
@RestController
public class RoleController implements RoleOpenApi {
    
    @Resource
    private RoleService service;
    
    @Override
    public JsonResponse<List<RoleVO>> addUserRole(ModifyUserRoleReq req) {
        return JsonResponse.ok (service.addUserRole (req));
    }
    
    @Override
    public JsonResponse<List<RoleVO>> delUserRole(ModifyUserRoleReq req) {
        return JsonResponse.ok (service.delUserRole (req));
    }
    
    @Override
    public JsonResponse<PageVO<RoleVO>> getRolePage(PageReq req) {
        return JsonResponse.ok (service.getRolePage (req));
    }
    
    @Override
    public JsonResponse<PageVO<RoleVO>> getUserRolePage(UserRoleQueryReq req) {
        return JsonResponse.ok (service.getUserRolePage (req));
    }
}
