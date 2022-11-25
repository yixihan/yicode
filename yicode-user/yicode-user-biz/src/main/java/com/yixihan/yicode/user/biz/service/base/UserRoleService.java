package com.yixihan.yicode.user.biz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.dal.pojo.base.UserRole;

import java.util.List;

/**
 * <p>
 * 用户角色对应表 服务类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 获取用户角色信息
     *
     * @param userId 用户 id
     * @return
     */
    List<RoleDtoResult> getUserRoleByUserId(Long userId);
}
