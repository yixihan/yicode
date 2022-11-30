package com.yixihan.yicode.user.biz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.user.api.dto.request.base.ModifyUserRoleDtoReq;
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
     * @return {@link RoleDtoResult}
     */
    List<RoleDtoResult> getUserRoleByUserId(Long userId);

    /**
     * 添加用户角色
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> addRole(ModifyUserRoleDtoReq dtoReq);

    /**
     * 删除用户角色
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> delRole(ModifyUserRoleDtoReq dtoReq);

}
