package com.yixihan.yicode.user.biz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.user.dal.pojo.base.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
public interface RoleService extends IService<Role> {

    /**
     * 通过 角色id 获取角色信息-列表查询
     *
     * @param roleIdList 角色 id
     * @return Role 集合
     */
    List<Role> getRoleList(List<Long> roleIdList);
}