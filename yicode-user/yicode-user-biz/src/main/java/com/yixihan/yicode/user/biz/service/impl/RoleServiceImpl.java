package com.yixihan.yicode.user.biz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.user.biz.service.RoleService;
import com.yixihan.yicode.user.dal.mapper.RoleMapper;
import com.yixihan.yicode.user.dal.pojo.Role;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Role> getRoleList(List<Long> roleIdList) {
        QueryWrapper<Role> wrapper = new QueryWrapper<> ();
        wrapper.in (!CollectionUtil.isEmpty (roleIdList), "role_id", roleIdList);
        List<Role> roleList = baseMapper.selectList (wrapper);
        return CollectionUtil.isEmpty (roleList) ? Collections.emptyList () : roleList;
    }

}
