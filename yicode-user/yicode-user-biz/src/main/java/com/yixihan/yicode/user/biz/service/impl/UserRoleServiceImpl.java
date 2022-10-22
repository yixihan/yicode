package com.yixihan.yicode.user.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.user.biz.service.UserRoleService;
import com.yixihan.yicode.user.dal.mapper.UserRoleMapper;
import com.yixihan.yicode.user.dal.pojo.UserRole;
import org.springframework.stereotype.Service;

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

}
