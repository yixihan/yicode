package com.yixihan.yicode.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.auth.mapper.UserRoleMapper;
import com.yixihan.yicode.auth.pojo.Role;
import com.yixihan.yicode.auth.pojo.UserRole;
import com.yixihan.yicode.auth.service.RoleService;
import com.yixihan.yicode.auth.service.UserRoleService;
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
 * @since 2022-10-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Resource
    private RoleService roleService;

    @Override
    public List<Role> getUserRoleByUserId(Long userId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<> ();
        wrapper.eq ("user_id", userId);
        List<UserRole> userRoleIdList = baseMapper.selectList (wrapper);
        // 提取用户 roleId 列表
        List<Long> roleIdList = new ArrayList<>();
        for (UserRole role : userRoleIdList) {
            roleIdList.add (role.getRoleId ());
        }
        return roleService.getRoleList (roleIdList);
    }

}
