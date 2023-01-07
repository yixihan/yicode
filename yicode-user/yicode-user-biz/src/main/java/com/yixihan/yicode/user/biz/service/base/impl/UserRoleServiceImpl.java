package com.yixihan.yicode.user.biz.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.base.ModifyUserRoleDtoReq;
import com.yixihan.yicode.user.api.dto.request.base.UserRoleQueryDtoReq;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.biz.service.base.RoleService;
import com.yixihan.yicode.user.biz.service.base.UserRoleService;
import com.yixihan.yicode.user.dal.mapper.base.UserRoleMapper;
import com.yixihan.yicode.user.dal.pojo.base.Role;
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
    public List<RoleDtoResult> getUserRoleByUserId(Long userId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<> ();
        wrapper.eq ("user_id", userId);
        List<UserRole> userRoleIdList = baseMapper.selectList (wrapper);
        // 提取用户 roleId 列表
        List<Long> roleIdList = userRoleIdList.stream ().map (UserRole::getRoleId).collect(Collectors.toList());
        List<Role> userRoleList = roleService.getRoleList (roleIdList);
        return CopyUtils.copyMulti (RoleDtoResult.class, userRoleList);
    }
    
    @Override
    public PageDtoResult<RoleDtoResult> getUserRoleByUserId(UserRoleQueryDtoReq dtoReq) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<> ();
        wrapper.eq ("user_id", dtoReq.getUserId ());
        Page<UserRole> userRolePage = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize ()),
                wrapper
        );
        // 提取用户 roleId 列表
        List<Long> roleIdList = userRolePage.getRecords ()
                .stream ().map (UserRole::getRoleId).collect(Collectors.toList());
        Page<Role> rolePage = roleService.getRolePage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize ()),
                roleIdList
        );
    
        return PageUtil.pageToPageDtoResult (
                rolePage,
                (o) -> CopyUtils.copySingle (RoleDtoResult.class, o)
        );
    }

    @Override
    public CommonDtoResult<Boolean> addRole(ModifyUserRoleDtoReq dtoReq) {
        if (roleService.hasRole (dtoReq.getRoleId ())) {
            throw new BizException ("无此角色!");
        }
        if (baseMapper.selectCount (new QueryWrapper<UserRole> ()
                .eq (UserRole.USER_ID, dtoReq.getUserId ())
                .eq (UserRole.ROLE_ID, dtoReq.getRoleId ())) > 0) {
            throw new BizException ("该用户已添加该角色!");
        }
        
        UserRole userRole = new UserRole ();
        userRole.setUserId (dtoReq.getUserId ());
        userRole.setRoleId (dtoReq.getRoleId ());
        int modify = baseMapper.insert (userRole);
        if (modify != 1) {
            throw new BizException (BizCodeEnum.FAILED_TYPE_BUSINESS);
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public CommonDtoResult<Boolean> delRole(ModifyUserRoleDtoReq dtoReq) {
        if (roleService.hasRole (dtoReq.getRoleId ())) {
            throw new BizException ("无此角色!");
        }
        QueryWrapper<UserRole> wrapper = new QueryWrapper<UserRole> ()
                .eq (UserRole.USER_ID, dtoReq.getUserId ())
                .eq (UserRole.ROLE_ID, dtoReq.getRoleId ());
        
        if (0 >= baseMapper.selectCount (wrapper)) {
            throw new BizException ("该用户无此角色!");
        }
        
        int modify = baseMapper.delete (wrapper);
        if (modify != 1) {
            throw new BizException (BizCodeEnum.FAILED_TYPE_BUSINESS);
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
}
