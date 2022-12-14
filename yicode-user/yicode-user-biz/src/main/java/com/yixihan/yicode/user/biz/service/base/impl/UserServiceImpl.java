package com.yixihan.yicode.user.biz.service.base.impl;

import cn.hutool.core.collection.CollectionUtil;
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
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.UserInfoDtoResult;
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
 * ????????? ???????????????
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
    public UserDetailInfoDtoResult getUserInfo(Long userId) {
        // ?????? id ??????????????????
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_ID, userId);
        User user = baseMapper.selectOne (wrapper);
        if (user == null) {
            return new UserDetailInfoDtoResult ();
        }
        UserDtoResult userDtoResult = CopyUtils.copySingle (UserDtoResult.class, user);
    
        // ????????????????????????
        List<RoleDtoResult> userRoleList = userRoleService.getUserRoleByUserId (userId);
    
        // ????????????????????????
        UserInfoDtoResult userInfoDtoResult = userInfoService.getUserInfo (userId);
        return new UserDetailInfoDtoResult (userDtoResult, userRoleList, userInfoDtoResult);
    }

    @Override
    public UserDtoResult getUserById(Long userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_ID, userId);
        User user = baseMapper.selectOne (wrapper);
        return CopyUtils.copySingle (
                UserDtoResult.class,
                Optional.ofNullable (user).orElse (new User ())
        );
    }

    @Override
    public UserDtoResult getUserByUserName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_NAME, userName);
        User user = baseMapper.selectOne (wrapper);
        return CopyUtils.copySingle (
                UserDtoResult.class,
                Optional.ofNullable (user).orElse (new User ())
        );
    }

    @Override
    public UserDtoResult getUserByMobile(String mobile) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_MOBILE, mobile);
        User user = baseMapper.selectOne (wrapper);
        return CopyUtils.copySingle (
                UserDtoResult.class,
                Optional.ofNullable (user).orElse (new User ())
        );
    }

    @Override
    public UserDtoResult getUserByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_EMAIL, email);
        User user =  Optional.ofNullable (baseMapper.selectOne (wrapper))
                .orElse (new User ());
        return CopyUtils.copySingle (UserDtoResult.class, user);
    }

    @Override
    public UserDtoResult getUserByToken(String token) {
        Object str = Optional
                .ofNullable (redisTemplate.opsForHash ().get (AuthConstant.USER_MAP_KEY, token))
                .orElse ("");
        
        if (StrUtil.isBlankIfStr (str)) {
            return new UserDtoResult ();
        }
        return JSONUtil.toBean (str.toString (), UserDtoResult.class);
    }

    @Override
    @Transactional(rollbackFor = BizException.class)
    public CommonDtoResult<Boolean> register(RegisterUserDtoReq dtoReq) {
        if (StrUtil.isBlank (dtoReq.getUserName ())) {
            dtoReq.setUserName ("??????_" + RandomUtil.randomString (5));
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

        // ???????????????
        flag = baseMapper.insert (user) == 1;
        if (!flag) {
            log.error ("????????????-?????????????????????!");
            throw new BizException ("????????????-?????????????????????!");
        }

        UserRole userRole = new UserRole ();
        userRole.setUserId (user.getUserId ());
        userRole.setRoleId (RoleEnums.USER.getRoleId ());

        // ?????????????????????
        flag = userRoleService.save (userRole);
        if (!flag) {
            log.error ("????????????-???????????????????????????!");
            throw new BizException ("????????????-???????????????????????????!");
        }

        UserInfo userInfo = new UserInfo ();
        userInfo.setUserId (user.getUserId ());

        // ?????????????????????
        flag = userInfoService.save (userInfo);
        if (!flag) {
            log.error ("????????????-???????????????????????????!");
            throw new BizException ("????????????-???????????????????????????!");
        }
    
        UserFavorite favorite = new UserFavorite ();
        favorite.setUserId (user.getUserId ());
        favorite.setFavoriteName ("???????????????");
        favorite.setFavoriteCount (0);
    
        // ????????????????????????
        flag = userFavoriteService.save (favorite);
        if (!flag) {
            log.error ("????????????-??????????????????????????????!");
            throw new BizException ("????????????-??????????????????????????????!");
        }

        return new CommonDtoResult<> (Boolean.TRUE, "??????????????????");
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
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "??????????????????!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "??????????????????");
    }

    @Override
    public CommonDtoResult<Boolean> bindEmail(BindEmailDtoReq dtoReq) {
        UserDtoResult userDtoResult = getUserById (dtoReq.getUserId ());
        if (!StrUtil.isBlank (userDtoResult.getUserEmail ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "??????????????????!");
        }

        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, dtoReq.getUserId ())
                .set (User.USER_EMAIL, dtoReq.getEmail ());
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "??????????????????!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "??????????????????");
    }

    @Override
    public CommonDtoResult<Boolean> unbindEmail(Long userId) {
        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, userId)
                .set (User.USER_EMAIL, null);
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "??????????????????!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "??????????????????");
    }

    @Override
    public CommonDtoResult<Boolean> bindMobile(BindMobileDtoReq dtoReq) {
        UserDtoResult userDtoResult = getUserById (dtoReq.getUserId ());
        if (!StrUtil.isBlank (userDtoResult.getUserMobile ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "?????????????????????!");
        }

        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, dtoReq.getUserId ())
                .set (User.USER_MOBILE, dtoReq.getMobile ());
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "?????????????????????!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "?????????????????????");
    }

    @Override
    public CommonDtoResult<Boolean> unbindMobile(Long userId) {
        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, userId)
                .set (User.USER_MOBILE, null);
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "?????????????????????!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "?????????????????????");
    }

    @Override
    public CommonDtoResult<Boolean> resetUserName(ResetUserNameDtoReq dtoReq) {
        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, dtoReq.getUserId ())
                .set (User.USER_NAME, dtoReq.getUserName ());
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "?????????????????????!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "?????????????????????");
    }

    @Override
    public CommonDtoResult<Boolean> cancellation(Long userId) {
        User user = new User ();

        UpdateWrapper<User> wrapper = new UpdateWrapper<> ();
        wrapper.eq (User.USER_ID, userId)
                .set (User.DEL_FLAG, 1);
        int modify = baseMapper.update (user, wrapper);
        if (modify != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, "????????????!");
        }
        return new CommonDtoResult<> (Boolean.TRUE, "????????????");
    }

    @Override
    public CommonDtoResult<Boolean> verifyUserName(String userName) {
        if (StrUtil.isBlank (userName)) {
            return new CommonDtoResult<> (Boolean.FALSE, "????????????????????????!");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_NAME, userName);
        boolean flag = baseMapper.selectCount (wrapper) == 0;
        if (!flag) {
            return new CommonDtoResult<> (Boolean.FALSE, "????????????????????????!");
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public CommonDtoResult<Boolean> verifyUserEmail(String email) {
        if (StrUtil.isBlank (email)) {
            return new CommonDtoResult<> (Boolean.FALSE, "?????????????????????!");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_EMAIL, email);
        boolean flag = baseMapper.selectCount (wrapper) == 0;
        if (!flag) {
            return new CommonDtoResult<> (Boolean.FALSE, "?????????????????????!");
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public CommonDtoResult<Boolean> verifyUserMobile(String mobile) {
        if (StrUtil.isBlank (mobile)) {
            return new CommonDtoResult<> (Boolean.FALSE, "????????????????????????!");
        }

        QueryWrapper<User> wrapper = new QueryWrapper<> ();
        wrapper.eq (User.USER_MOBILE, mobile);
        boolean flag = baseMapper.selectCount (wrapper) == 0;
        if (!flag) {
            return new CommonDtoResult<> (Boolean.FALSE, "????????????????????????!");
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
}
