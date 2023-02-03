package com.yixihan.yicode.user.openapi.biz.service.base.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.ValidationUtils;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;
import com.yixihan.yicode.common.enums.thirdpart.code.CodeTypeEnums;
import com.yixihan.yicode.common.enums.thirdpart.sms.VerificationCodeEnums;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.RoleDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.common.enums.user.LoginTypeEnums;
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
        List<RoleDtoResult> userRoleDtoResult = userRoleFeignClient.getUserRoleList (userId).getResult ();
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
        String token = request.getHeader (AuthConstant.JWT_TOKEN_HEADER)
                .substring (AuthConstant.TOKEN_SUB_INDEX);
        return userFeignClient.getUserByToken (token).getResult ();
    }
    
    @Override
    public UserDtoResult getUser(Long userId) {
        return userFeignClient.getUserByUserId (userId).getResult ();
    }
    
    @Override
    public synchronized CommonVO<Boolean> register(RegisterUserReq req) {
        // 合法性校验
        if (LoginTypeEnums.USERNAME_PASSWORD.getType ().equals (req.getType ())) {
            // 用户名+密码注册
            if (!ValidationUtils.validateUserName (req.getUserName ())) {
                throw new BizException ("用户名不符合规范!");
            }
            if (!ValidationUtils.validatePassword (req.getPassword ())) {
                throw new BizException ("密码不符合规范!");
            }
            if (!userFeignClient.verifyUserName (req.getUserName ()).getResult ().getData ()) {
                throw new BizException ("用户名已被注册!");
            }
        } else if (LoginTypeEnums.EMAIL_CODE.getType ().equals (req.getType ())) {
            // 邮箱+验证码注册
            if (!ValidationUtils.validateEmail (req.getEmail ())) {
                throw new BizException ("邮箱不符合规范!");
            }
            CommonDtoResult<Boolean> emailDtoResult = userFeignClient.verifyUserEmail (req.getEmail ()).getResult ();
            if (!emailDtoResult.getData ()) {
                throw new BizException (emailDtoResult.getMessage ());
            }
            // 校验验证码
            EmailValidateDtoReq emailDtoReq = new EmailValidateDtoReq ();
            emailDtoReq.setEmail (req.getEmail ());
            emailDtoReq.setCode (req.getCode ());
            emailDtoReq.setEmailType (CodeTypeEnums.REGISTER.getType ());
            if (!emailFeignClient.validate (emailDtoReq).getResult ().getData ()) {
                throw new BizException ("验证码错误!");
            }
        } else if (LoginTypeEnums.MOBILE_CODE.getType ().equals (req.getType ())) {
            // 手机号+验证码注册
            if (!ValidationUtils.validateMobile (req.getMobile ())) {
                throw new BizException ("手机号不符合规范!");
            }
            CommonDtoResult<Boolean> mobileDtoResult = userFeignClient.verifyUserMobile (req.getMobile ()).getResult ();
            if (!mobileDtoResult.getData ()) {
                throw new BizException (mobileDtoResult.getMessage ());
            }
            // 校验验证码
            SMSValidateDtoReq smsDtoReq = new SMSValidateDtoReq ();
            smsDtoReq.setMobile (req.getMobile ());
            smsDtoReq.setCode (req.getCode ());
            smsDtoReq.setMobileType (CodeTypeEnums.REGISTER.getType ());
            if (!smsFeignClient.validate (smsDtoReq).getResult ().getData ()) {
                throw new BizException ("验证码错误!");
            }
        } else {
            log.error ("错误的注册方式!", new BizException (800001, "错误的注册方式!"));
            throw new BizException ("错误的注册方式!");
        }
        RegisterUserDtoReq dtoReq = BeanUtil.toBean (req, RegisterUserDtoReq.class);
        CommonDtoResult<Boolean> dtoResult = userFeignClient.register (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> resetPassword(ResetPasswordReq req) {
        // 校验密码复杂度
        if (!ValidationUtils.validatePassword (req.getNewPassword ())) {
            throw new BizException ("密码不符合规范!");
        }
        
        // 校验验证码
        if (VerificationCodeEnums.EMAIL.getMethod ().equals (req.getType ())) {
            if (!ValidationUtils.validateEmail (req.getEmail ())) {
                throw new BizException ("邮箱不符合规范!");
            }
            EmailValidateDtoReq emailDtoReq = new EmailValidateDtoReq ();
            emailDtoReq.setEmail (req.getEmail ());
            emailDtoReq.setCode (req.getCode ());
            emailDtoReq.setEmailType (CodeTypeEnums.RESET_PASSWORD.getType ());
            if (!emailFeignClient.validate (emailDtoReq).getResult ().getData ()) {
                throw new BizException ("验证码错误!");
            }
        } else if (VerificationCodeEnums.SMS.getMethod ().equals (req.getType ())) {
            if (!ValidationUtils.validateMobile (req.getMobile ())) {
                throw new BizException ("手机号不符合规范!");
            }
            SMSValidateDtoReq smsDtoReq = new SMSValidateDtoReq ();
            smsDtoReq.setMobile (req.getMobile ());
            smsDtoReq.setCode (req.getCode ());
            smsDtoReq.setMobileType (CodeTypeEnums.RESET_PASSWORD.getType ());
            if (!smsFeignClient.validate (smsDtoReq).getResult ().getData ()) {
                throw new BizException ("验证码错误!");
            }
        } else {
            log.error ("错误的验证码方式!", new BizException (800001, "错误的验证码方式!"));
            throw new BizException ("错误的验证码方式!");
        }

        ResetPasswordDtoReq dtoReq = BeanUtil.toBean (req, ResetPasswordDtoReq.class);

        CommonDtoResult<Boolean> dtoResult = userFeignClient.resetPassword (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> bindEmail(EmailReq req) {
        // 校验邮箱
        CommonDtoResult<Boolean> emailDtoResult = userFeignClient.verifyUserEmail (req.getEmail ()).getResult ();
        if (!emailDtoResult.getData ()) {
            throw new BizException (emailDtoResult.getMessage ());
        }
        
        // 校验验证码
        EmailValidateDtoReq emailDtoReq = new EmailValidateDtoReq ();
        emailDtoReq.setEmail (req.getEmail ());
        emailDtoReq.setCode (req.getCode ());
        emailDtoReq.setEmailType (CodeTypeEnums.COMMON.getType ());
        if (!emailFeignClient.validate (emailDtoReq).getResult ().getData ()) {
            throw new BizException ("验证码错误!");
        }
        
        BindEmailDtoReq dtoReq = BeanUtil.toBean (req, BindEmailDtoReq.class);
        dtoReq.setUserId (getUser ().getUserId ());
        CommonDtoResult<Boolean> dtoResult = userFeignClient.bindEmail (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> unbindEmail(EmailReq req) {
        // 校验邮箱
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        
        // 校验验证码
        EmailValidateDtoReq emailDtoReq = new EmailValidateDtoReq ();
        emailDtoReq.setEmail (req.getEmail ());
        emailDtoReq.setCode (req.getCode ());
        emailDtoReq.setEmailType (CodeTypeEnums.COMMON.getType ());
        if (!emailFeignClient.validate (emailDtoReq).getResult ().getData ()) {
            throw new BizException ("验证码错误!");
        }
        CommonDtoResult<Boolean> dtoResult = userFeignClient.unbindEmail (getUser ().getUserId ()).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> bindMobile(MobileReq req) {
        // 校验手机号
        CommonDtoResult<Boolean> mobileDtoResult = userFeignClient.verifyUserMobile (req.getMobile ()).getResult ();
        if (!mobileDtoResult.getData ()) {
            throw new BizException (mobileDtoResult.getMessage ());
        }
        
        // 校验验证码
        SMSValidateDtoReq smsDtoReq = new SMSValidateDtoReq ();
        smsDtoReq.setMobile (req.getMobile ());
        smsDtoReq.setCode (req.getCode ());
        smsDtoReq.setMobileType (CodeTypeEnums.COMMON.getType ());
        if (!smsFeignClient.validate (smsDtoReq).getResult ().getData ()) {
            throw new BizException ("验证码错误!");
        }
        BindMobileDtoReq dtoReq = BeanUtil.toBean (req, BindMobileDtoReq.class);
        dtoReq.setUserId (getUser ().getUserId ());
        CommonDtoResult<Boolean> dtoResult = userFeignClient.bindMobile (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> unbindMobile(MobileReq req) {
        // 校验手机号
        if (!ValidationUtils.validateMobile (req.getMobile ())) {
            throw new BizException ("手机号不符合规范!");
        }
        
        SMSValidateDtoReq smsDtoReq = new SMSValidateDtoReq ();
        smsDtoReq.setMobile (req.getMobile ());
        smsDtoReq.setCode (req.getCode ());
        smsDtoReq.setMobileType (CodeTypeEnums.COMMON.getType ());
        if (!smsFeignClient.validate (smsDtoReq).getResult ().getData ()) {
            throw new BizException ("验证码错误!");
        }
        CommonDtoResult<Boolean> dtoResult = userFeignClient.unbindMobile (getUser ().getUserId ()).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> resetUserName(ResetUserNameReq req) {
        if (!ValidationUtils.validateUserName (req.getUserName ())) {
            throw new BizException ("用户名不符合规范!");
        }
        
        if (!userFeignClient.verifyUserName (req.getUserName ()).getResult ().getData ()) {
            throw new BizException ("用户名已被占用!");
        }

        ResetUserNameDtoReq dtoReq = BeanUtil.toBean (req, ResetUserNameDtoReq.class);
        dtoReq.setUserId (getUser ().getUserId ());

        CommonDtoResult<Boolean> dtoResult = userFeignClient.resetUserName (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);

    }

    @Override
    public CommonVO<Boolean> cancellation(Long userId) {
        if (!verifyUserId (userId)) {
            throw new BizException ("该用户不存在!");
        }
        
        CommonDtoResult<Boolean> dtoResult = userFeignClient.cancellation (userId).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public Boolean verifyUserId(Long userId) {
        UserDtoResult user = getUser (userId);
        return user.getUserId () != null;
    }
    
    /**
     * 组装 {@link UserDetailInfoVO}
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
}
