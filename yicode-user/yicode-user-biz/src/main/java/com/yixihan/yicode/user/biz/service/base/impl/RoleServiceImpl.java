package com.yixihan.yicode.user.biz.service.base.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.biz.service.base.RoleService;
import com.yixihan.yicode.user.biz.service.base.UserRoleService;
import com.yixihan.yicode.user.dal.mapper.base.RoleMapper;
import com.yixihan.yicode.user.dal.pojo.base.Role;
import com.yixihan.yicode.user.dal.pojo.base.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private UserRoleService userRoleService;
    
    @Override
    public List<Role> getRoleList(List<Long> roleIdList) {
        QueryWrapper<Role> wrapper = new QueryWrapper<> ();
        wrapper.in (!CollectionUtil.isEmpty (roleIdList), "role_id", roleIdList);
        List<Role> roleList = baseMapper.selectList (wrapper);
        return CollectionUtil.isEmpty (roleList) ? Collections.emptyList () : roleList;
    }
    
    @Override
    public Page<Role> getRolePage(Page<Role> page, List<Long> roleIdList) {
        QueryWrapper<Role> wrapper = new QueryWrapper<> ();
        wrapper.in (!CollectionUtil.isEmpty (roleIdList), "role_id", roleIdList);
        return baseMapper.selectPage (page, wrapper);
    }
    
    @Override
    public PageDtoResult<RoleDtoResult> getRoleList(PageDtoReq dtoReq) {
        Page<Role> rolePage = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize ()),
                null);
        return PageUtil.pageToPageDtoResult (
                Optional.ofNullable (rolePage).orElse (new Page<> ()),
                (o) -> CopyUtils.copySingle (RoleDtoResult.class, o)
        );
    }
    
    @Override
    public CommonDtoResult<Boolean> addRole(String roleName) {
        Role role = new Role ();
        role.setRoleName (roleName);
        int modify = baseMapper.insert (role);
        if (modify == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }
    
    @Override
    public CommonDtoResult<Boolean> delRole(Long roleId) {
        if (hasRole (roleId)) {
            return new CommonDtoResult<> (Boolean.FALSE, "无此角色！");
        }
        
        if (userRoleService.count (new QueryWrapper<UserRole> ()
                .eq (UserRole.ROLE_ID, roleId)) > 0) {
            return new CommonDtoResult<> (Boolean.FALSE, "该角色还有绑定用户,请先解绑再删除！");
        }
        
        QueryWrapper<Role> wrapper = new QueryWrapper<Role> ()
                .eq (Role.ROLE_ID, roleId);
    
        int modify = baseMapper.delete (wrapper);
        if (modify == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }
    
    public Boolean hasRole (Long roleId) {
        QueryWrapper<Role> wrapper = new QueryWrapper<> ();
        wrapper.eq (Role.ROLE_ID, roleId);
        return baseMapper.selectCount (wrapper) <= 0;
    }
    
}
