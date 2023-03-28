package com.yixihan.yicode.user.biz.service.base.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.enums.thirdpart.sms.VerificationCodeEnums;
import com.yixihan.yicode.common.enums.user.RoleEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
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
        User user = getById (userId);
        Assert.notNull (user, BizCodeEnum.ACCOUNT_NOT_FOUND);
    
        return BeanUtil.toBean (user, UserDtoResult.class);
    }
    
    @Override
    public UserDtoResult getUserByUserName(String userName) {
        User user = lambdaQuery ()
                .eq (User::getUserName, userName)
                .one ();
        Assert.notNull (user, BizCodeEnum.ACCOUNT_NOT_FOUND);
    
        return BeanUtil.toBean (user, UserDtoResult.class);
    }
    
    @Override
    public UserDtoResult getUserByMobile(String mobile) {
        User user = lambdaQuery ()
                .eq (User::getUserMobile, mobile)
                .one ();
        Assert.notNull (user, BizCodeEnum.ACCOUNT_NOT_FOUND);
    
        return BeanUtil.toBean (user, UserDtoResult.class);
    }
    
    @Override
    public UserDtoResult getUserByEmail(String email) {
        User user = lambdaQuery ()
                .eq (User::getUserEmail, email)
                .one ();
        Assert.notNull (user, BizCodeEnum.ACCOUNT_NOT_FOUND);
    
        return BeanUtil.toBean (user, UserDtoResult.class);
    }
    
    @Override
    public UserDtoResult getUserByToken(String token) {
        return getUserById (getUserIdByToken (token));
    }
    
    @Override
    public Long getUserIdByToken(String token) {
        String str = Optional
                .ofNullable (redisTemplate.opsForHash ().get (AuthConstant.USER_MAP_KEY, token))
                .orElse ("")
                .toString ();
    
        if (StrUtil.isBlank (str)) {
            throw new BizException (BizCodeEnum.TOKEN_EXPIRED);
        }
    
        UserDtoResult tokenInfo = JSONUtil.toBean (str, UserDtoResult.class);
        return tokenInfo.getUserId ();
    }
    
    @Override
    public List<UserDtoResult> getUserListByUserId(List<Long> userIdList) {
        List<User> userList = listByIds (userIdList);
        userList = CollUtil.isEmpty (userList) ? Collections.emptyList () : userList;
        
        return BeanUtil.copyToList (userList, UserDtoResult.class);
    }
    
    @Override
    public PageDtoResult<UserDtoResult> getUserList(QueryUserDtoReq dtoReq) {
        Page<User> page = lambdaQuery ()
                .eq (dtoReq.getUserId () != null, User::getUserId, dtoReq.getUserId ())
                .eq (StrUtil.isNotBlank (dtoReq.getUserName ()), User::getUserName, dtoReq.getUserName ())
                .eq (StrUtil.isNotBlank (dtoReq.getUserMobile ()), User::getUserMobile, dtoReq.getUserMobile ())
                .eq (StrUtil.isNotBlank (dtoReq.getUserEmail ()), User::getUserEmail, dtoReq.getUserEmail ())
                .orderByAsc (User::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
        
        return PageUtil.pageToPageDtoResult (
                page,
                o -> BeanUtil.toBean (o, UserDtoResult.class)
        );
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
    @Transactional(rollbackFor = BizException.class)
    public void register(RegisterUserDtoReq dtoReq) {
        // 设置默认字段
        if (StrUtil.isBlank (dtoReq.getUserName ())) {
            dtoReq.setUserName ("用户_" + RandomUtil.randomString (5));
        }
        if (StrUtil.isBlank (dtoReq.getPassword ())) {
            dtoReq.setPassword (RandomUtil.randomString (10));
        }
        
        // 插入用户表
        User user = new User ();
        user.setUserName (dtoReq.getUserName ());
        user.setUserPassword (passwordEncoder.encode (dtoReq.getPassword ()));
        user.setUserEmail (dtoReq.getEmail ());
        user.setUserMobile (dtoReq.getMobile ());
        user.setRegisterType (dtoReq.getType ());
    
        Assert.isTrue (save (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
        // 插入用户权限表
        UserRole userRole = new UserRole ();
        userRole.setUserId (user.getUserId ());
        userRole.setRoleId (RoleEnums.USER.getRoleId ());
        
        Assert.isTrue (userRoleService.save (userRole), BizCodeEnum.FAILED_TYPE_BUSINESS);

    
        // 插入用户信息表
        UserInfo userInfo = new UserInfo ();
        userInfo.setUserId (user.getUserId ());
    
        Assert.isTrue (userInfoService.save (userInfo), BizCodeEnum.FAILED_TYPE_BUSINESS);
    
        // 插入用户收藏夹表
        UserFavorite favorite = new UserFavorite ();
        favorite.setUserId (user.getUserId ());
        favorite.setFavoriteName ("默认收藏夹");
        favorite.setFavoriteType ("QUESTION");
        favorite.setFavoriteCount (0);
    
        Assert.isTrue (userFavoriteService.save (favorite), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void resetPassword(ResetPasswordDtoReq dtoReq) {
        User user;
        if (VerificationCodeEnums.EMAIL.getMethod ().equals (dtoReq.getType ())) {
            user = BeanUtil.toBean (getUserByEmail (dtoReq.getEmail ()), User.class);
        } else if (VerificationCodeEnums.SMS.getMethod ().equals (dtoReq.getType ())) {
            user = BeanUtil.toBean (getUserByMobile (dtoReq.getMobile ()), User.class);
        } else {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 加密密码
        user.setUserPassword (passwordEncoder.encode (dtoReq.getNewPassword ()));

        // 更新密码
        Assert.isTrue (updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void bindEmail(BindEmailDtoReq dtoReq) {
        User user = BeanUtil.toBean (getUserById (dtoReq.getUserId ()), User.class);
        user.setUserEmail (dtoReq.getEmail ());
        
        // 更新邮箱
        Assert.isTrue (updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void unbindEmail(Long userId) {
        User user = BeanUtil.toBean (getUserById (userId), User.class);
        user.setUserEmail (null);
    
        // 更新邮箱
        Assert.isTrue (updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void bindMobile(BindMobileDtoReq dtoReq) {
        User user = BeanUtil.toBean (getUserById (dtoReq.getUserId ()), User.class);
        user.setUserMobile (dtoReq.getMobile ());
    
        // 更新手机号
        Assert.isTrue (updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void unbindMobile(Long userId) {
        User user = BeanUtil.toBean (getUserById (userId), User.class);
        user.setUserMobile (null);
    
        // 更新手机号
        Assert.isTrue (updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void resetUserName(ResetUserNameDtoReq dtoReq) {
        User user = BeanUtil.toBean (getUserById (dtoReq.getUserId ()), User.class);
        user.setUserName (dtoReq.getUserName ());
        
        // 更新用户名
        Assert.isTrue (updateById (user), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public void cancellation(Long userId) {
        Assert.isTrue (removeById (userId), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }
    
    @Override
    public Boolean verifyUserName(String userName) {
        return lambdaQuery ()
                .eq (User::getUserName, userName)
                .count () <= 0;
    }
    
    @Override
    public Boolean verifyUserEmail(String email) {
        return lambdaQuery ()
                .eq (User::getUserEmail, email)
                .count () <= 0;
    }
    
    @Override
    public Boolean verifyUserMobile(String mobile) {
        return lambdaQuery ()
                .eq (User::getUserMobile, mobile)
                .count () <= 0;
    }
}
