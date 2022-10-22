package com.yixihan.yicode.user.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.user.api.dto.response.RoleDtoResult;
import com.yixihan.yicode.user.api.dto.response.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.UserDtoResult;
import com.yixihan.yicode.user.api.dto.response.UserInfoDtoResult;
import com.yixihan.yicode.user.biz.service.UserRoleService;
import com.yixihan.yicode.user.biz.service.UserService;
import com.yixihan.yicode.user.dal.mapper.UserMapper;
import com.yixihan.yicode.user.dal.pojo.Role;
import com.yixihan.yicode.user.dal.pojo.User;
import com.yixihan.yicode.user.dal.pojo.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserRoleService userRoleService;

    @Override
    public UserDetailInfoDtoResult getUserInfo(Long userId) {
        // 根据 id 查询用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq ("user_id", userId);
        User user = baseMapper.selectOne (wrapper);
        if (user == null) {
            return new UserDetailInfoDtoResult ();
        }

        List<Role> userRoleList = userRoleService.getUserRoleByUserId (userId);

        // TODO 查询用户资料信息
        UserInfo userInfo = new UserInfo ();

        // 转换实体类
        UserDtoResult userDtoResult = CopyUtils.copySingle (UserDtoResult.class, user);
        List<RoleDtoResult> roleDtoResultList = CopyUtils.copyMulti (RoleDtoResult.class, userRoleList);
        UserInfoDtoResult userInfoDtoResult = CopyUtils.copySingle (UserInfoDtoResult.class, userInfo);
        return new UserDetailInfoDtoResult (userDtoResult, roleDtoResultList, userInfoDtoResult);
    }
}
