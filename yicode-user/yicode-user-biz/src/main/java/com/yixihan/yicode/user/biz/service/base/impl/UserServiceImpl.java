package com.yixihan.yicode.user.biz.service.base.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.enums.thirdpart.sms.VerificationCodeEnums;
import com.yixihan.yicode.common.enums.user.RoleEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.admin.AdminDataDtoReq;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.biz.service.base.UserRoleService;
import com.yixihan.yicode.user.biz.service.base.UserService;
import com.yixihan.yicode.user.biz.service.extra.UserFavoriteService;
import com.yixihan.yicode.user.biz.service.extra.UserInfoService;
import com.yixihan.yicode.user.dal.mapper.base.UserMapper;
import com.yixihan.yicode.user.dal.pojo.base.User;
import com.yixihan.yicode.user.dal.pojo.base.UserRole;
import com.yixihan.yicode.user.dal.pojo.extra.UserFavorite;
import com.yixihan.yicode.user.dal.pojo.extra.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    private UserFavoriteService userFavoriteService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDtoResult getUserById(Long userId) {
        User user = baseMapper.selectById (userId);
        return BeanUtil.toBean (
                Optional.ofNullable (user).orElse (new User ()),
                UserDtoResult.class
        );
    }
    
    @Override
    public List<UserDtoResult> getUserListByUserId(List<Long> userIdList) {
        List<User> userList = baseMapper.selectBatchIds (userIdList);
        if (CollectionUtil.isEmpty (userList)) {
            return new ArrayList<> ();
        }
        return BeanUtil.copyToList (userList, UserDtoResult.class);
    }
    
    @Override
    public UserDtoResult getUserByUserName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_NAME, userName);
        User user = baseMapper.selectOne (wrapper);
        return BeanUtil.toBean (
                Optional.ofNullable (user).orElse (new User ()),
                UserDtoResult.class
        );
    }

    @Override
    public UserDtoResult getUserByMobile(String mobile) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_MOBILE, mobile);
        User user = baseMapper.selectOne (wrapper);
        return BeanUtil.toBean (
                Optional.ofNullable (user).orElse (new User ()),
                UserDtoResult.class
        );
    }

    @Override
    public UserDtoResult getUserByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_EMAIL, email);
        User user =  Optional.ofNullable (baseMapper.selectOne (wrapper))
                .orElse (new User ());
        return BeanUtil.toBean (user, UserDtoResult.class);
    }

    @Override
    public UserDtoResult getUserByToken(String token) {
        Object str = Optional
                .ofNullable (redisTemplate.opsForHash ().get (AuthConstant.USER_MAP_KEY, token))
                .orElse ("");
        
        if (StrUtil.isBlankIfStr (str)) {
            return new UserDtoResult ();
        }
        UserDtoResult tokenInfo = JSONUtil.toBean (str.toString (), UserDtoResult.class);
        return getUserById (tokenInfo.getUserId ());
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
        boolean flag;
        // 插入用户表
        User user = new User ();
        user.setUserName (dtoReq.getUserName ());
        user.setUserPassword (passwordEncoder.encode (dtoReq.getPassword ()));
        user.setUserEmail (dtoReq.getEmail ());
        user.setUserMobile (dtoReq.getMobile ());
        user.setRegisterType (dtoReq.getType ());

        flag = baseMapper.insert (user) == 1;
        if (!flag) {
            log.error ("用户注册-用户表插入失败!");
            throw new BizException ("用户注册-用户表插入失败!");
        }
    
        // 插入用户权限表
        UserRole userRole = new UserRole ();
        userRole.setUserId (user.getUserId ());
        userRole.setRoleId (RoleEnums.USER.getRoleId ());

        flag = userRoleService.save (userRole);
        if (!flag) {
            log.error ("用户注册-用户权限表插入失败!");
            throw new BizException ("用户注册-用户权限表插入失败!");
        }
    
        // 插入用户信息表
        UserInfo userInfo = new UserInfo ();
        userInfo.setUserId (user.getUserId ());

        flag = userInfoService.save (userInfo);
        if (!flag) {
            log.error ("用户注册-用户信息表插入失败!");
            throw new BizException ("用户注册-用户信息表插入失败!");
        }
    
        // 插入用户收藏夹表
        UserFavorite favorite = new UserFavorite ();
        favorite.setUserId (user.getUserId ());
        favorite.setFavoriteName ("默认收藏夹");
        favorite.setFavoriteType ("QUESTION");
        favorite.setFavoriteCount (0);
    
        flag = userFavoriteService.save (favorite);
        if (!flag) {
            log.error ("用户注册-用户收藏夹表插入失败!");
            throw new BizException ("用户注册-用户收藏夹表插入失败!");
        }

        return new CommonDtoResult<> (Boolean.TRUE, "用户注册成功");
    }

    @Override
    public CommonDtoResult<Boolean> resetPassword(ResetPasswordDtoReq dtoReq) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        User user;
        if (VerificationCodeEnums.EMAIL.getMethod ().equals (dtoReq.getType ())) {
            wrapper.eq (User.USER_EMAIL, dtoReq.getEmail ());
            user = BeanUtil.toBean (getUserByEmail (dtoReq.getEmail ()), User.class);
        } else if (VerificationCodeEnums.SMS.getMethod ().equals (dtoReq.getType ())) {
            wrapper.eq (User.USER_MOBILE, dtoReq.getMobile ());
            user = BeanUtil.toBean (getUserByEmail (dtoReq.getEmail ()), User.class);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.PARAMS_VALID_ERR.getErrorMsg ());
        }
        wrapper.set (User.USER_PASSWORD,passwordEncoder.encode (dtoReq.getNewPassword ()));
        
        
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "密码重置失败!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "重置密码成功");
    }

    @Override
    public CommonDtoResult<Boolean> bindEmail(BindEmailDtoReq dtoReq) {
        User user = BeanUtil.toBean (getUserById (dtoReq.getUserId ()), User.class);
        if (StrUtil.isNotBlank (user.getUserEmail ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "请先解绑邮箱!");
        }
    
        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, dtoReq.getUserId ())
                .set (User.USER_EMAIL, dtoReq.getEmail ());
        
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "绑定邮箱失败!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "绑定邮箱成功");
    }

    @Override
    public CommonDtoResult<Boolean> unbindEmail(Long userId) {
        User user = BeanUtil.toBean (getUserById (userId), User.class);
        if (StrUtil.isBlank (user.getUserEmail ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "请先绑定邮箱!");
        }
        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, userId)
                .set (User.USER_EMAIL, null);
        
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "解绑邮箱失败!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "解绑邮箱成功");
    }

    @Override
    public CommonDtoResult<Boolean> bindMobile(BindMobileDtoReq dtoReq) {
        User user = BeanUtil.toBean (getUserById (dtoReq.getUserId ()), User.class);
        if (StrUtil.isNotBlank (user.getUserMobile ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "请先解绑手机号!");
        }
        
        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, dtoReq.getUserId ())
                .set (User.USER_MOBILE, dtoReq.getMobile ());
        
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "绑定手机号失败!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "绑定手机号成功");
    }

    @Override
    public CommonDtoResult<Boolean> unbindMobile(Long userId) {
        User user = BeanUtil.toBean (getUserById (userId), User.class);
        if (StrUtil.isBlank (user.getUserMobile ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "请先绑定手机号!");
        }
        
        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, userId)
                .set (User.USER_MOBILE, null);
        
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "解绑手机号失败!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "解绑手机号成功");
    }

    @Override
    public CommonDtoResult<Boolean> resetUserName(ResetUserNameDtoReq dtoReq) {
        User user = BeanUtil.toBean (getUserById (dtoReq.getUserId ()), User.class);
    
        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, dtoReq.getUserId ())
                .set (User.USER_NAME, dtoReq.getUserName ());
        
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "修改用户名失败!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "修改用户名成功");
    }

    @Override
    public CommonDtoResult<Boolean> cancellation(Long userId) {
        User user = BeanUtil.toBean (getUserById (userId), User.class);
        
        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, userId)
                .set (User.DEL_FLAG, 1);
        
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "注销失败!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "注销成功");
    }

    @Override
    public CommonDtoResult<Boolean> verifyUserName(String userName) {
        if (StrUtil.isBlank (userName)) {
            return new CommonDtoResult<> (Boolean.FALSE, "校验的用户名为空!");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_NAME, userName);
        boolean flag = baseMapper.selectCount (wrapper) == 0;
        if (!flag) {
            return new CommonDtoResult<> (Boolean.FALSE, "该用户名已被占用!");
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public CommonDtoResult<Boolean> verifyUserEmail(String email) {
        if (StrUtil.isBlank (email)) {
            return new CommonDtoResult<> (Boolean.FALSE, "校验的邮箱为空!");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_EMAIL, email);
        boolean flag = baseMapper.selectCount (wrapper) == 0;
        if (!flag) {
            return new CommonDtoResult<> (Boolean.FALSE, "该邮箱已被绑定!");
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public CommonDtoResult<Boolean> verifyUserMobile(String mobile) {
        if (StrUtil.isBlank (mobile)) {
            return new CommonDtoResult<> (Boolean.FALSE, "校验的手机号为空!");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_MOBILE, mobile);
        boolean flag = baseMapper.selectCount (wrapper) == 0;
        if (!flag) {
            return new CommonDtoResult<> (Boolean.FALSE, "该手机号已被绑定!");
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }
    
    @Override
    public List<UserCommonDtoResult> getUserCommonInfo(List<Long> userIdList) {
        if (CollectionUtil.isEmpty (userIdList)) {
            return Collections.emptyList ();
        }
        List<UserCommonDtoResult> dtoResultList = baseMapper.getUserCommonInfo (userIdList);
        return CollectionUtil.isEmpty (dtoResultList) ?
                Collections.emptyList () :
                dtoResultList;
    }
    
    @Override
    public CommonDtoResult<Integer> brokenData(AdminDataDtoReq dtoReq) {
        Integer count = lambdaQuery ()
                .between (User::getCreateTime, dtoReq.getStartDate (), dtoReq.getEndDate ())
                .count ();
        
        return new CommonDtoResult<> (count);
    }
    
    
    @Override
    public PageDtoResult<Long> getUserList(PageDtoReq dtoReq) {
        Page<User> userIdPage = lambdaQuery ()
                .select (User::getUserId)
                .page (new Page<> (dtoReq.getPage (), dtoReq.getPageSize (), dtoReq.getSearchCount ()));
        
        return PageUtil.pageToPageDtoResult (userIdPage, User::getUserId);
    }
}
