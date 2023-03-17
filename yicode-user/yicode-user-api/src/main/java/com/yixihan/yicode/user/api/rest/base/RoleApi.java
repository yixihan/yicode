package com.yixihan.yicode.user.api.rest.base;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 角色 api
 *
 * @author yixihan
 * @date 2022/11/25 14:52
 */
@Api(tags = "角色 api")
@RequestMapping("/role")
public interface RoleApi {
    
    @ApiOperation("添加角色")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<RoleDtoResult> addRole (@RequestBody String roleName);
    
    @ApiOperation ("删除角色")
    @PostMapping(value = "/del", produces = "application/json")
    void delRole (@RequestBody Long roleId);
    
    @ApiOperation ("判断有无该角色")
    @PostMapping(value = "/validate", produces = "application/json")
    ApiResult<Boolean> validateRole(@RequestBody Long roleId);
    
    @ApiOperation ("获取角色信息")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<RoleDtoResult> roleDetail(@RequestBody Long roleId);
    
    @ApiOperation ("获取角色信息-分页查询")
    @PostMapping(value = "/detail/page", produces = "application/json")
    ApiResult<PageDtoResult<RoleDtoResult>> rolePageDetail(@RequestBody PageDtoReq dtoReq);
    
    @ApiOperation ("获取角色信息-列表查询")
    @PostMapping(value = "/detail/list", produces = "application/json")
    ApiResult<List<RoleDtoResult>> roleListDetail();
}
