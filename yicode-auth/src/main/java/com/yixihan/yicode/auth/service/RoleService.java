package com.yixihan.yicode.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.auth.pojo.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
public interface RoleService extends IService<Role> {

    /**
     * 通过 角色id 获取角色信息 - 列表查询
     * @param roleIdList
     * @return
     */
    List<String> getRoleList(List<Long> roleIdList);
}
