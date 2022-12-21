package com.yixihan.yicode.user.biz.service.base.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.enums.RoleEnums;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.thirdpart.api.enums.sms.VerificationCodeEnums;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserInfoService;
import com.yixihan.yicode.user.biz.service.base.UserRoleService;
import com.yixihan.yicode.user.biz.service.base.UserService;
import com.yixihan.yicode.user.dal.mapper.base.UserMapper;
import com.yixihan.yicode.user.dal.pojo.base.User;
import com.yixihan.yicode.user.dal.pojo.extra.UserInfo;
import com.yixihan.yicode.user.dal.pojo.base.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDetailInfoDtoResult getUserInfo(Long userId) {
        // 根据 id 查询用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_ID, userId);
        User user = baseMapper.selectOne (wrapper);
        if (user == null) {
            return new UserDetailInfoDtoResult ();
        }
        UserDtoResult userDtoResult = CopyUtils.copySingle (UserDtoResult.class, user);
    
        // 查询用户角色信息
        List<RoleDtoResult> userRoleList = userRoleService.getUserRoleByUserId (userId);
    
        // 查询用户资料信息
        UserInfoDtoResult userInfoDtoResult = userInfoService.getUserInfo (userId);
        return new UserDetailInfoDtoResult (userDtoResult, userRoleList, userInfoDtoResult);
    }

    @Override
    public UserDtoResult getUserById(Long userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_ID, userId);
        User user = baseMapper.selectOne (wrapper);
        return user == null ? new UserDtoResult () : CopyUtils.copySingle (UserDtoResult.class, user);
    }

    @Override
    public UserDtoResult getUserByUserName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_NAME, userName);
        User user = baseMapper.selectOne (wrapper);
        return user == null ? new UserDtoResult () : CopyUtils.copySingle (UserDtoResult.class, user);
    }

    @Override
    public UserDtoResult getUserByMobile(String mobile) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_MOBILE, mobile);
        User user = baseMapper.selectOne (wrapper);
        return user == null ? new UserDtoResult () : CopyUtils.copySingle (UserDtoResult.class, user);
    }

    @Override
    public UserDtoResult getUserByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_EMAIL, email);
        User user = baseMapper.selectOne (wrapper);
        return user == null ? new UserDtoResult () : CopyUtils.copySingle (UserDtoResult.class, user);
    }

    @Override
    public UserDtoResult getUserByToken(String token) {
        String str = Objects.requireNonNull (redisTemplate.opsForHash ().get (AuthConstant.USER_MAP_KEY, token)).toString ();
        return JSONUtil.toBean (str, UserDtoResult.class);
    }

    @Override
    @Transactional(rollbackFor = BizException.class)
    public CommonDtoResult<Boolean> register(RegisterUserDtoReq dtoReq) {
        if (StrUtil.isBlank (dtoReq.getUserName ())) {
            dtoReq.setUserName ("用户_" + RandomUtil.randomString (5));
        }
        if (StrUtil.isBlank (dtoReq.getPassword ())) {
            dtoReq.setPassword (RandomUtil.randomString (10));
        }
        User user = new User ();
        boolean flag;
        user.setUserName (dtoReq.getUserName ());
        user.setUserPassword (passwordEncoder.encode (dtoReq.getPassword ()));
        user.setUserEmail (dtoReq.getEmail ());
        user.setUserMobile (dtoReq.getMobile ());
        user.setRegisterType (dtoReq.getType ());

        // 插入用户表
        flag = baseMapper.insert (user) == 1;
        if (!flag) {
            log.error ("用户注册-用户表插入失败!");
            throw new BizException ("用户注册-用户表插入失败!");
        }

        UserRole userRole = new UserRole ();
        userRole.setUserId (user.getUserId ());
        userRole.setRoleId (RoleEnums.USER.getRoleId ());

        // 插入用户权限表
        flag = userRoleService.save (userRole);
        if (!flag) {
            log.error ("用户注册-用户权限表插入失败!");
            throw new BizException ("用户注册-用户权限表插入失败!");
        }

        UserInfo userInfo = new UserInfo ();
        userInfo.setUserId (user.getUserId ());

        // 插入用户信息表
        flag = userInfoService.save (userInfo);
        if (!flag) {
            log.error ("用户注册-用户信息表插入失败!");
            throw new BizException ("用户注册-用户信息表插入失败!");
        }

        return new CommonDtoResult<> (true, "用户注册成功");
    }

    @Override
    public CommonDtoResult<Boolean> resetPassword(ResetPasswordDtoReq dtoReq) {
        User user = new User ();
        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        if (VerificationCodeEnums.EMAIL.getMethod ().equals (dtoReq.getType ())) {
            wrapper.eq (User.USER_EMAIL, dtoReq.getEmail ());
        } else if (VerificationCodeEnums.SMS.getMethod ().equals (dtoReq.getType ())) {
            wrapper.eq (User.USER_MOBILE, dtoReq.getMobile ());
        }
        wrapper.set (User.USER_PASSWORD,passwordEncoder.encode (dtoReq.getNewPassword ()));
        return new CommonDtoResult<> (baseMapper.update (user, wrapper) == 1, "重置密码成功");
    }

    @Override
    public CommonDtoResult<Boolean> bindEmail(BindEmailDtoReq dtoReq) {
        UserDtoResult userDtoResult = getUserById (dtoReq.getUserId ());
        if (!StrUtil.isBlank (userDtoResult.getUserEmail ())) {
            return new CommonDtoResult<> (false, "请先解绑邮箱");
        }

        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, dtoReq.getUserId ())
                .set (User.USER_EMAIL, dtoReq.getEmail ());
        return new CommonDtoResult<> (baseMapper.update (user, wrapper) == 1, "绑定邮箱成功");
    }

    @Override
    public CommonDtoResult<Boolean> unbindEmail(Long userId) {
        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, userId)
                .set (User.USER_EMAIL, null);
        return new CommonDtoResult<> (baseMapper.update (user, wrapper) == 1, "解绑邮箱成功");
    }

    @Override
    public CommonDtoResult<Boolean> bindMobile(BindMobileDtoReq dtoReq) {
        UserDtoResult userDtoResult = getUserById (dtoReq.getUserId ());
        if (!StrUtil.isBlank (userDtoResult.getUserMobile ())) {
            return new CommonDtoResult<> (false, "请先解绑手机号");
        }

        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, dtoReq.getUserId ())
                .set (User.USER_MOBILE, dtoReq.getMobile ());
        return new CommonDtoResult<> (baseMapper.update (user, wrapper) == 1, "绑定手机号成功");
    }

    @Override
    public CommonDtoResult<Boolean> unbindMobile(Long userId) {
        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, userId)
                .set (User.USER_MOBILE, null);
        return new CommonDtoResult<> (baseMapper.update (user, wrapper) == 1, "解绑手机号成功");
    }

    @Override
    public CommonDtoResult<Boolean> resetUserName(ResetUserNameDtoReq dtoReq) {
        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, dtoReq.getUserId ())
                .set (User.USER_NAME, dtoReq.getUserName ());
        return new CommonDtoResult<> (baseMapper.update (user, wrapper) == 1, "修改用户名成功");
    }

    @Override
    public CommonDtoResult<Boolean> cancellation(Long userId) {
        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, userId)
                .set (User.DEL_FLAG, 1);
        return new CommonDtoResult<> (baseMapper.update (user, wrapper) == 1, "注销成功");
    }

    @Override
    public CommonDtoResult<Boolean> verifyUserName(String userName) {
        if (StrUtil.isBlank (userName)) {
            return new CommonDtoResult<> (false, "校验的用户名为空!");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_NAME, userName);
        return new CommonDtoResult<> (baseMapper.selectCount (wrapper) == 0);
    }

    @Override
    public CommonDtoResult<Boolean> verifyUserEmail(String email) {
        if (StrUtil.isBlank (email)) {
            return new CommonDtoResult<> (false, "校验的用户名为空!");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_EMAIL, email);
        return new CommonDtoResult<> (baseMapper.selectCount (wrapper) == 0);
    }

    @Override
    public CommonDtoResult<Boolean> verifyUserMobile(String mobile) {
        if (StrUtil.isBlank (mobile)) {
            return new CommonDtoResult<> (false, "校验的用户名为空!");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_MOBILE, mobile);
        return new CommonDtoResult<> (baseMapper.selectCount (wrapper) == 0);
    }
}
