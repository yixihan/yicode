package com.yixihan.yicode.user.biz.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.biz.service.base.RoleService;
import com.yixihan.yicode.user.biz.service.base.UserRoleService;
import com.yixihan.yicode.user.dal.mapper.base.UserRoleMapper;
import com.yixihan.yicode.user.dal.pojo.base.Role;
import com.yixihan.yicode.user.dal.pojo.base.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
        List<Long> roleIdList = new ArrayList<> ();
        for (UserRole role : userRoleIdList) {
            roleIdList.add (role.getRoleId ());
        }
        List<Role> userRoleList = roleService.getRoleList (roleIdList);
        return CopyUtils.copyMulti (RoleDtoResult.class, userRoleList);
    }
}
