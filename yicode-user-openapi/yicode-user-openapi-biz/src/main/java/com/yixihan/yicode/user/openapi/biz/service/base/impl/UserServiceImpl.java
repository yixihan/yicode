package com.yixihan.yicode.user.openapi.biz.service.base.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.enums.thirdpart.code.CodeTypeEnums;
import com.yixihan.yicode.common.enums.thirdpart.sms.VerificationCodeEnums;
import com.yixihan.yicode.common.enums.user.LoginTypeEnums;
import com.yixihan.yicode.common.enums.user.RoleEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.common.util.ValidationUtils;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.openapi.api.vo.request.base.*;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserVO;
import com.yixihan.yicode.user.openapi.biz.feign.thirdpart.email.EmailFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.thirdpart.sms.SMSFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.base.UserFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.base.UserRoleFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * 用户 服务实现类
 *
 * @author yixihan
 * @date 2022-10-22-18:07
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserFeignClient userFeignClient;
    
    @Resource
    private UserInfoService userInfoService;
    
    @Resource
    private UserRoleFeignClient userRoleFeignClient;

    @Resource
    private EmailFeignClient emailFeignClient;

    @Resource
    private SMSFeignClient smsFeignClient;

    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetailInfoVO getUserDetailInfo(Long userId) {
        // 获取用户基础信息
        UserDtoResult userDtoResult = userFeignClient.getUserByUserId (userId).getResult ();
        // 获取用户资料
        UserInfoVO userInfoVO = userInfoService.getUserInfo (userId);
        // 获取用户角色
        List<RoleDtoResult> userRoleDtoResult = Collections.emptyList ();
        if (userRoleFeignClient.getUserRoleList (getUserId ()).getResult ().stream().anyMatch (
                o -> RoleEnums.ADMIN.getRoleId ().equals (o.getRoleId ()) ||
                        RoleEnums.SUPER_ADMIN.getRoleId ().equals (o.getRoleId ())
        )) {
            userRoleDtoResult = userRoleFeignClient.getUserRoleList (userId).getResult ();
        }
        // 获取用户角色
        return getUserDetailInfoVO (userDtoResult, userInfoVO, userRoleDtoResult);
    }
    
    @Override
    public UserDetailInfoVO getUserDetailInfo() {
        UserDtoResult result = getUser ();
        if (result.getUserId () == null) {
            return new UserDetailInfoVO ();
        }
        return getUserDetailInfo (result.getUserId ());
    }
    
    @Override
    public UserDtoResult getUser() {
        String token = request.getHeader (AuthConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isBlank (token)) {
            return null;
        }
        token = token.substring (AuthConstant.TOKEN_SUB_INDEX);
        return userFeignClient.getUserByToken (token).getResult ();
    }
    
    @Override
    public Long getUserId() {
        String token = request.getHeader (AuthConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isBlank (token)) {
            return null;
        }
        token = token.substring (AuthConstant.TOKEN_SUB_INDEX);
        return userFeignClient.getUserIdByToken (token).getResult ();
    }
    
    @Override
    public UserDtoResult getUser(Long userId) {
        return userFeignClient.getUserByUserId (userId).getResult ();
    }
    
    @Override
    public PageVO<UserDetailInfoVO> getUserList(PageReq req) {
        PageDtoReq dtoReq = BeanUtil.toBean (req, PageDtoReq.class);
        PageDtoResult<Long> dtoResult = userFeignClient.getUserList (dtoReq).getResult ();
        PageVO<Long> userIdPage = PageVOUtil.pageDtoToPageVO (dtoResult, o -> o);
        PageVO<UserDetailInfoVO> pageVO = PageVOUtil.convertPageVO (userIdPage, o -> null);
        userIdPage.getRecords ().parallelStream ().forEach (userId ->
                pageVO.getRecords ().add (getUserDetailInfo (userId))
        );
        
        return pageVO;
    }
    
    @Override
    public synchronized void register(RegisterUserReq req) {
        // 合法性校验
        if (LoginTypeEnums.USERNAME_PASSWORD.getType ().equals (req.getType ())) {
            // 用户名+密码注册
            Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateUserName (req.getUserName ())),
                    new BizException ("用户名不符合规范!"));
            Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validatePassword (req.getPassword ())),
                    new BizException ("密码不符合规范!"));
            Assert.isTrue (Boolean.TRUE.equals (userFeignClient.verifyUserName (req.getUserName ()).getResult ()),
                    new BizException ("用户名已被占用!"));
        } else if (LoginTypeEnums.EMAIL_CODE.getType ().equals (req.getType ())) {
            // 邮箱+验证码注册
            Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                    BizCodeEnum.EMAIL_VALIDATE_ERR);
            Assert.isTrue (Boolean.TRUE.equals (userFeignClient.verifyUserEmail (req.getEmail ()).getResult ()),
                    new BizException ("该邮箱已被绑定!"));
            Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);

            // 校验验证码
            validateEmailCode (req.getEmail (), req.getCode (), CodeTypeEnums.REGISTER);
    
        } else if (LoginTypeEnums.MOBILE_CODE.getType ().equals (req.getType ())) {
            // 手机号+验证码注册
            Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                    BizCodeEnum.MOBILE_VALIDATE_ERR);
            Assert.isTrue (Boolean.TRUE.equals (userFeignClient.verifyUserMobile (req.getMobile ()).getResult ()),
                    new BizException ("该手机号已被绑定!"));
            Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);

            // 校验验证码
            validateMobileCode (req.getMobile (), req.getCode (), CodeTypeEnums.REGISTER);
        } else {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 注册
        RegisterUserDtoReq dtoReq = BeanUtil.toBean (req, RegisterUserDtoReq.class);
        userFeignClient.register (dtoReq);
    }

    @Override
    public void resetPassword(ResetPasswordReq req) {
        // 校验密码复杂度
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validatePassword (req.getNewPassword ())),
                new BizException ("密码不符合规范!"));
        
        // 校验验证码
        if (VerificationCodeEnums.EMAIL.getMethod ().equals (req.getType ())) {
            Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                    BizCodeEnum.EMAIL_VALIDATE_ERR);
            Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
    
            validateEmailCode (req.getEmail (), req.getCode (), CodeTypeEnums.RESET_PASSWORD);
        } else if (VerificationCodeEnums.SMS.getMethod ().equals (req.getType ())) {
            Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                    BizCodeEnum.MOBILE_VALIDATE_ERR);
            Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
    
            validateMobileCode (req.getMobile (), req.getCode (), CodeTypeEnums.RESET_PASSWORD);
        } else {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 重置密码
        ResetPasswordDtoReq dtoReq = BeanUtil.toBean (req, ResetPasswordDtoReq.class);
        userFeignClient.resetPassword (dtoReq);
    }

    @Override
    public void bindEmail(EmailReq req) {
        // 校验邮箱
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
        Assert.isTrue (Boolean.TRUE.equals (userFeignClient.verifyUserEmail (req.getEmail ()).getResult ()),
                new BizException ("该邮箱已被绑定!"));
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
        
        // 校验验证码
        validateEmailCode (req.getEmail (), req.getCode (), CodeTypeEnums.COMMON);
    
        // 绑定邮箱
        BindEmailDtoReq dtoReq = BeanUtil.toBean (req, BindEmailDtoReq.class);
        dtoReq.setUserId (getUser ().getUserId ());
        userFeignClient.bindEmail (dtoReq);
    }

    @Override
    public void unbindEmail(EmailReq req) {
        // 校验邮箱
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
        
        // 校验验证码
        CodeTypeEnums type = CodeTypeEnums.COMMON;
        validateEmailCode (req.getEmail (), req.getCode (), type);
    
        // 解绑邮箱
        userFeignClient.unbindEmail (getUser ().getUserId ());
    }
    
    @Override
    public void bindMobile(MobileReq req) {
        // 校验手机号
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);
        Assert.isTrue (Boolean.TRUE.equals (userFeignClient.verifyUserMobile (req.getMobile ()).getResult ()),
                new BizException ("该手机号已被绑定!"));
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
        
        // 校验验证码
        validateMobileCode (req.getMobile (), req.getCode (), CodeTypeEnums.COMMON);
    
        // 绑定手机号
        BindMobileDtoReq dtoReq = BeanUtil.toBean (req, BindMobileDtoReq.class);
        dtoReq.setUserId (getUser ().getUserId ());
        userFeignClient.bindMobile (dtoReq);
    }
    
    @Override
    public void unbindMobile(MobileReq req) {
        // 校验手机号
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
        
        // 校验验证码
        validateMobileCode (req.getMobile (), req.getCode (), CodeTypeEnums.COMMON);
    
        // 解绑手机号
        userFeignClient.unbindMobile (getUser ().getUserId ());
    }
    
    @Override
    public void resetUserName(ResetUserNameReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateUserName (req.getUserName ())),
                new BizException ("用户名不符合规范!"));
        Assert.isTrue (Boolean.TRUE.equals (userFeignClient.verifyUserName (req.getUserName ()).getResult ()),
                new BizException ("用户名已被占用!"));

        // 更新用户名
        ResetUserNameDtoReq dtoReq = BeanUtil.toBean (req, ResetUserNameDtoReq.class);
        dtoReq.setUserId (getUser ().getUserId ());
        userFeignClient.resetUserName (dtoReq);
    }
    
    @Override
    public void cancellation(Long userId) {
        // 参数校验
        Assert.isTrue (verifyUserId (userId), BizCodeEnum.ACCOUNT_NOT_FOUND);
        
        // 注销
        userFeignClient.cancellation (userId);
    }
    
    @Override
    public Boolean verifyUserId(Long userId) {
        UserDtoResult user = getUser (userId);
        return user != null && user.getUserId () != null;
    }
    
    /**
     * 组装 {@link UserDetailInfoVO}
     *
     * @param userDtoResult {@link UserDtoResult}
     * @param userInfoVO {@link UserInfoVO}
     * @param userRoleDtoResult {@link RoleDtoResult}
     * @return {@link UserDetailInfoVO}
     */
    private UserDetailInfoVO getUserDetailInfoVO(UserDtoResult userDtoResult, UserInfoVO userInfoVO, List<RoleDtoResult> userRoleDtoResult) {
        UserVO userVO = BeanUtil.toBean (userDtoResult, UserVO.class);
        List<RoleVO> userRoleList = BeanUtil.copyToList (userRoleDtoResult, RoleVO.class);
        return new UserDetailInfoVO (userVO, userRoleList, userInfoVO);
    }
    
    /**
     * 校验短信验证码
     *
     * @param mobile 手机号
     * @param code 验证码
     * @param type 发送类型
     */
    private void validateMobileCode(String mobile, String code, CodeTypeEnums type) {
        SMSValidateDtoReq smsDtoReq = new SMSValidateDtoReq ();
        smsDtoReq.setMobile (mobile);
        smsDtoReq.setCode (code);
        smsDtoReq.setMobileType (type.getType ());
        smsFeignClient.validate (smsDtoReq);
    }
    
    /**
     * 校验邮箱验证码
     *
     * @param email 邮箱
     * @param code 验证码
     * @param type 发送类型
     */
    private void validateEmailCode(String email, String code, CodeTypeEnums type) {
        EmailValidateDtoReq emailDtoReq = new EmailValidateDtoReq ();
        emailDtoReq.setEmail (email);
        emailDtoReq.setCode (code);
        emailDtoReq.setEmailType (type.getType ());
        emailFeignClient.validate (emailDtoReq);
    }
}
