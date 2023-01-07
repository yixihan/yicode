package com.yixihan.yicode.user.openapi.biz.service.base.impl;

import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.ValidationUtils;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.enums.code.CodeTypeEnums;
import com.yixihan.yicode.thirdpart.api.enums.sms.VerificationCodeEnums;
import com.yixihan.yicode.user.api.dto.request.base.*;
import com.yixihan.yicode.user.api.dto.response.base.UserDetailInfoDtoResult;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import com.yixihan.yicode.user.openapi.api.enums.LoginTypeEnums;
import com.yixihan.yicode.user.openapi.api.vo.request.base.*;
import com.yixihan.yicode.user.openapi.api.vo.response.base.RoleVO;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserDetailInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserInfoVO;
import com.yixihan.yicode.user.openapi.api.vo.response.base.UserVO;
import com.yixihan.yicode.user.openapi.biz.feign.thirdpart.email.EmailFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.thirdpart.sms.SMSFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.base.UserFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
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
    private EmailFeignClient emailFeignClient;

    @Resource
    private SMSFeignClient smsFeignClient;

    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetailInfoVO getUserInfo(Long userId) {
        UserDetailInfoDtoResult dtoResult = userFeignClient.getUserInfo (userId).getResult ();
        return getUserDetailInfoVO (dtoResult);
    }

    @Override
    public UserDetailInfoVO getUserInfo() {
        // 获取请求头附带的 token
        String token = request.getHeader (AuthConstant.JWT_TOKEN_HEADER)
                .substring (AuthConstant.TOKEN_SUB_INDEX);
        UserDtoResult result = userFeignClient.getUserByToken (token).getResult ();
        if (result.getUserId () == null) {
            return new UserDetailInfoVO ();
        }
        UserDetailInfoDtoResult dtoResult = userFeignClient.getUserInfo (result.getUserId ()).getResult ();
        return getUserDetailInfoVO (dtoResult);
    }

    @Override
    public CommonVO<Boolean> register(RegisterUserReq req) {
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
            if (!userFeignClient.verifyUserEmail (req.getEmail ()).getResult ().getData ()) {
                throw new BizException ("邮箱已被绑定!");
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
            if (!userFeignClient.verifyUserMobile (req.getMobile ()).getResult ().getData ()) {
                throw new BizException ("手机号已被绑定!");
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
        RegisterUserDtoReq dtoReq = CopyUtils.copySingle (RegisterUserDtoReq.class, req);
        CommonDtoResult<Boolean> dtoResult = userFeignClient.register (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> resetPassword(ResetPasswordReq req) {
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

        if (!ValidationUtils.validatePassword (req.getNewPassword ())) {
            throw new BizException ("密码不符合规范!");
        }

        ResetPasswordDtoReq dtoReq = CopyUtils.copySingle (ResetPasswordDtoReq.class, req);

        CommonDtoResult<Boolean> dtoResult = userFeignClient.resetPassword (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> bindEmail(EmailReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailValidateDtoReq emailDtoReq = new EmailValidateDtoReq ();
        emailDtoReq.setEmail (req.getEmail ());
        emailDtoReq.setCode (req.getCode ());
        emailDtoReq.setEmailType (CodeTypeEnums.COMMON.getType ());
        if (!emailFeignClient.validate (emailDtoReq).getResult ().getData ()) {
            throw new BizException ("验证码错误!");
        }
        UserDetailInfoVO userInfo = getUserInfo ();
        BindEmailDtoReq dtoReq = CopyUtils.copySingle (BindEmailDtoReq.class, req);
        dtoReq.setUserId (userInfo.getUser ().getUserId ());
        CommonDtoResult<Boolean> dtoResult = userFeignClient.bindEmail (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> unbindEmail(EmailReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailValidateDtoReq emailDtoReq = new EmailValidateDtoReq ();
        emailDtoReq.setEmail (req.getEmail ());
        emailDtoReq.setCode (req.getCode ());
        emailDtoReq.setEmailType (CodeTypeEnums.COMMON.getType ());
        if (!emailFeignClient.validate (emailDtoReq).getResult ().getData ()) {
            throw new BizException ("验证码错误!");
        }
        UserDetailInfoVO userInfo = getUserInfo ();
        CommonDtoResult<Boolean> dtoResult = userFeignClient.unbindEmail (userInfo.getUser ().getUserId ()).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> bindMobile(MobileReq req) {
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
        UserDetailInfoVO userInfo = getUserInfo ();
        BindMobileDtoReq dtoReq = CopyUtils.copySingle (BindMobileDtoReq.class, req);
        dtoReq.setUserId (userInfo.getUser ().getUserId ());
        CommonDtoResult<Boolean> dtoResult = userFeignClient.bindMobile (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> unbindMobile(MobileReq req) {
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
        UserDetailInfoVO userInfo = getUserInfo ();
        CommonDtoResult<Boolean> dtoResult = userFeignClient.unbindMobile (userInfo.getUser ().getUserId ()).getResult ();
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

        ResetUserNameDtoReq dtoReq = CopyUtils.copySingle (ResetUserNameDtoReq.class, req);
        UserDetailInfoVO userInfo = getUserInfo ();
        dtoReq.setUserId (userInfo.getUser ().getUserId ());

        CommonDtoResult<Boolean> dtoResult = userFeignClient.resetUserName (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);

    }

    @Override
    public CommonVO<Boolean> cancellation(Long userId) {
        CommonDtoResult<Boolean> dtoResult = userFeignClient.cancellation (userId).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public Boolean verifyUserId(Long userId) {
        UserDetailInfoVO userInfo = getUserInfo (userId);
        return userInfo.getUser () == null;
    }
    
    /**
     * 组装 {@link UserDetailInfoVO}
     * @param dtoResult {@link UserDetailInfoDtoResult}
     * @return {@link UserDetailInfoVO}
     */
    private UserDetailInfoVO getUserDetailInfoVO(UserDetailInfoDtoResult dtoResult) {
        UserVO userVO = CopyUtils.copySingle (UserVO.class, dtoResult.getUser ());
        List<RoleVO> roleVOList = CopyUtils.copyMulti (RoleVO.class, dtoResult.getUserRoleList ());
        UserInfoVO userInfoVO = CopyUtils.copySingle (UserInfoVO.class, dtoResult.getUserInfo ());
        return new UserDetailInfoVO (userVO, roleVOList, userInfoVO);
    }
}
