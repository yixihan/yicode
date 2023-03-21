package com.yixihan.yicode.user.biz.service.base.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.enums.user.RoleEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
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
    public RoleDtoResult addRole(String roleName) {
        Role role = new Role ();
        role.setRoleName (roleName);
    
        // 保存
        Assert.isTrue (save (role), BizCodeEnum.FAILED_TYPE_BUSINESS);
        // 处理 roleName
        dealRoleEnums (CollUtil.toList (role));
    
        return BeanUtil.toBean (role, RoleDtoResult.class);
    }
    
    @Override
    public void delRole(Long roleId) {
        // 校验该角色是否还有绑定用户
        Integer count = userRoleService.lambdaQuery ()
                .eq (UserRole::getRoleId, roleId)
                .count ();
        Assert.isTrue (count <= 0, new BizException ("该角色还有绑定用户,请先解绑再删除!"));
    
        // 删除
        Assert.isTrue (removeById (roleId), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public Boolean validateRole(Long roleId) {
        return lambdaQuery ()
                .eq (Role::getRoleId, roleId)
                .count () > 0;
    }
    
    @Override
    public RoleDtoResult roleDetail(Long roleId) {
        Role role = getById (roleId);
    
        Assert.notNull (role, new BizException ("没有该角色"));
        // 处理 roleName
        dealRoleEnums (CollUtil.toList (role));
    
        return BeanUtil.toBean (role, RoleDtoResult.class);
    }
    
    @Override
    public PageDtoResult<RoleDtoResult> getRolePage(PageDtoReq dtoReq) {
        return getRolePage (dtoReq, Collections.emptyList ());
    }
    
    @Override
    public List<RoleDtoResult> getRoleList() {
        return getRoleList (Collections.emptyList ());
    }
    
    @Override
    public List<RoleDtoResult> getRoleList(List<Long> roleIdList) {
        List<Role> roleList = lambdaQuery ()
                .in (CollUtil.isNotEmpty (roleIdList), Role::getRoleId, roleIdList)
                .list ();
        roleList = CollUtil.isEmpty (roleList) ? Collections.emptyList () : roleList;
        
        // 处理 roleName
        dealRoleEnums (roleList);
    
        return BeanUtil.copyToList (roleList, RoleDtoResult.class);
    }
    
    @Override
    public PageDtoResult<RoleDtoResult> getRolePage(PageDtoReq dtoReq, List<Long> roleIdList) {
        Page<Role> page = lambdaQuery ()
                .in (CollUtil.isNotEmpty (roleIdList), Role::getRoleId, roleIdList)
                .page (PageUtil.toPage (dtoReq));
        
        // 处理 roleName
        dealRoleEnums (page.getRecords ());
        
        return PageUtil.pageToPageDtoResult (
                page,
                o -> BeanUtil.toBean (o, RoleDtoResult.class)
        );
    }
    
    private void dealRoleEnums (List<Role> roleList) {
        roleList.forEach (item ->
            item.setRoleName (RoleEnums.valueOf (item.getRoleName ()).getRoleDesc ())
        );
    }
}
