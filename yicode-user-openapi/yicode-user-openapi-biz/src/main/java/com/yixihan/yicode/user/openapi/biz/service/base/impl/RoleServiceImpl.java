package com.yixihan.yicode.user.openapi.biz.service.base.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.user.api.dto.request.base.ModifyUserRoleDtoReq;
import com.yixihan.yicode.user.api.dto.request.base.UserRoleQueryDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.base.AddRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.ModifyUserRoleReq;
import com.yixihan.yicode.user.openapi.api.vo.request.base.UserRoleQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;
import com.yixihan.yicode.user.openapi.biz.feign.user.base.RoleFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.base.UserRoleFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.RoleService;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色服务实现类
 *
 * @author yixihan
 * @date 2022/12/21 9:43
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    
    @Resource
    private RoleFeignClient roleFeignClient;
    
    @Resource
    private UserRoleFeignClient userRoleFeignClient;
    
    @Resource
    private UserService userService;
    
    @Override
    public CommonVO<Boolean> addRole(AddRoleReq req) {
        // 校验角色名是否合规(不为空&不重复)
        if (StrUtil.isBlank (req.getRoleName ())) {
            throw new BizException ("角色名不能为空!");
        }
        // 校验是否已有该角色名
        if (roleFeignClient.getRoleList ().getResult ()
                .stream ().anyMatch ((o) -> o.getRoleName ().equals (req.getRoleName ()))) {
            throw new BizException ("已有该角色!");
        }
        
        // 添加角色
        CommonDtoResult<Boolean> dtoResult = roleFeignClient.addRole (req.getRoleName ()).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delRole(Long roleId) {
        // 校验角色 ID 是否合规(需存在)
        if (!roleFeignClient.hasRole (roleId).getResult ().getData ()) {
            throw new BizException ("该角色不存在!");
        }
        
        // 删除角色
        CommonDtoResult<Boolean> dtoResult = roleFeignClient.delRole (roleId).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> addUserRole(ModifyUserRoleReq req) {
        // 校验角色 ID 是否合规(需存在)
        if (!roleFeignClient.hasRole (req.getRoleId ()).getResult ().getData ()) {
            throw new BizException ("该角色不存在!");
        }
        // 校验用户 ID 是否合规(需存在)
        if (!userService.verifyUserId (req.getUserId ())) {
            throw new BizException ("该用户不存在!");
        }
        
        // 用户添加角色
        ModifyUserRoleDtoReq dtoReq = BeanUtil.toBean (req, ModifyUserRoleDtoReq.class);
        CommonDtoResult<Boolean> dtoResult = userRoleFeignClient.addRole (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delUserRole(ModifyUserRoleReq req) {
        // 校验角色 ID 是否合规(需存在)
        if (!roleFeignClient.hasRole (req.getRoleId ()).getResult ().getData ()) {
            throw new BizException ("该角色不存在!");
        }
        // 校验用户 ID 是否合规(需存在)
        if (!userService.verifyUserId (req.getUserId ())) {
            throw new BizException ("该用户不存在!");
        }
        
        // 删除用户角色
        ModifyUserRoleDtoReq dtoReq = new ModifyUserRoleDtoReq (req.getUserId (), req.getRoleId ());
        
        CommonDtoResult<Boolean> dtoResult = userRoleFeignClient.delRole (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public PageVO<RoleVO> getRolePage(PageReq req) {
        PageDtoReq dtoReq = BeanUtil.toBean (req, PageDtoReq.class);
        PageDtoResult<RoleDtoResult> dtoResult = roleFeignClient.getRolePage (dtoReq).getResult ();
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, RoleVO.class)
        );
    }
    
    @Override
    public PageVO<RoleVO> getUserRolePage(UserRoleQueryReq req) {
        UserRoleQueryDtoReq dtoReq = BeanUtil.toBean (req, UserRoleQueryDtoReq.class);
        PageDtoResult<RoleDtoResult> dtoResult = userRoleFeignClient.getUserRolePage (dtoReq).getResult ();
    
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> BeanUtil.toBean (o, RoleVO.class)
        );
    }
}
