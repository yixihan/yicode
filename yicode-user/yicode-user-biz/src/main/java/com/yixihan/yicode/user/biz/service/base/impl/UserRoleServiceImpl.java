package com.yixihan.yicode.user.biz.service.base.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.user.api.dto.request.base.ModifyUserRoleDtoReq;
import com.yixihan.yicode.user.api.dto.request.base.UserRoleQueryDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.biz.service.base.RoleService;
import com.yixihan.yicode.user.biz.service.base.UserRoleService;
import com.yixihan.yicode.user.dal.mapper.base.UserRoleMapper;
import com.yixihan.yicode.user.dal.pojo.base.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色对应表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Resource
    private RoleService roleService;
    
    @Override
    public List<RoleDtoResult> addRole(ModifyUserRoleDtoReq dtoReq) {
        // 角色 id 校验
        Assert.isTrue (roleService.validateRole (dtoReq.getRoleId ()), new BizException ("没有该角色!"));
        // 用户-角色校验
        Integer count = lambdaQuery ()
                .eq (UserRole::getUserId, dtoReq.getUserId ())
                .eq (UserRole::getRoleId, dtoReq.getRoleId ())
                .count ();
        Assert.isTrue (count <= 0, new BizException ("该用户已添加该角色!"));
    
        // 给用户添加角色
        UserRole userRole = new UserRole ();
        userRole.setUserId (dtoReq.getUserId ());
        userRole.setRoleId (dtoReq.getRoleId ());
        Assert.isTrue (save (userRole), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return getUserRoleByUserId (dtoReq.getUserId ());
    }
    
    @Override
    public List<RoleDtoResult> delRole(ModifyUserRoleDtoReq dtoReq) {
        // 用户-角色校验
        Integer count = lambdaQuery ()
                .eq (UserRole::getUserId, dtoReq.getUserId ())
                .eq (UserRole::getRoleId, dtoReq.getRoleId ())
                .count ();
        Assert.isTrue (count > 0, new BizException ("该用户无此角色!"));
    
        // 获取主键 id
        Long id =  lambdaQuery ()
                .eq (UserRole::getUserId, dtoReq.getUserId ())
                .eq (UserRole::getRoleId, dtoReq.getRoleId ())
                .one ()
                .getRoleId ();
    
        // 删除
        Assert.isTrue (removeById (id), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return getUserRoleByUserId (dtoReq.getUserId ());
    }
    
    @Override
    public List<RoleDtoResult> getUserRoleByUserId(Long userId) {
        // 获取用户的角色 id 列表
        List<Long> userRoleIdlist = lambdaQuery ()
                .eq (UserRole::getUserId, userId)
                .list ()
                .stream ()
                .map (UserRole::getRoleId)
                .collect(Collectors.toList());
        
        return roleService.getRoleList (userRoleIdlist);
    }
    
    @Override
    public PageDtoResult<RoleDtoResult> getUserRoleByUserId(UserRoleQueryDtoReq dtoReq) {
        // 获取用户的角色 id 列表
        List<Long> userRoleIdlist = lambdaQuery ()
                .eq (UserRole::getUserId, dtoReq.getUserId ())
                .list ()
                .stream ()
                .map (UserRole::getRoleId)
                .collect(Collectors.toList());
        
        return roleService.getRolePage (dtoReq, userRoleIdlist);
    }
}
