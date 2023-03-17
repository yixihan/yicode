package com.yixihan.yicode.thirdpart.openapi.biz.service.email.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.enums.thirdpart.code.CodeTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.ValidationUtils;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.email.EmailFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.user.user.UserFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.email.EmailService;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 邮件模块 服务实现类
 *
 * @author yixihan
 * @date 2022/11/23 14:56
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private EmailFeignClient emailFeignClient;
    
    @Resource
    private UserFeignClient userFeignClient;

    @Override
    public void loginSend(EmailSendReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
        
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByEmail (req.getEmail ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        send (req, CodeTypeEnums.LOGIN);
    }
    
    @Override
    public void loginValidate(EmailValidateReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
        
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByEmail (req.getEmail ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        // 校验邮箱验证码
        validate (req, CodeTypeEnums.LOGIN);
    }
    
    @Override
    public void registerSend(EmailSendReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
    
        // 发送邮件
        send (req, CodeTypeEnums.REGISTER);
    }
    
    @Override
    public void registerValidate(EmailValidateReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
    
        // 校验邮箱验证码
        validate (req, CodeTypeEnums.REGISTER);
    }
    
    @Override
    public void resetSend(EmailSendReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
    
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByEmail (req.getEmail ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        // 发送邮件
        send (req, CodeTypeEnums.RESET_PASSWORD);
    }
    
    @Override
    public void resetValidate(EmailValidateReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
    
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByEmail (req.getEmail ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        // 校验邮箱验证码
        validate (req, CodeTypeEnums.RESET_PASSWORD);
    }
    
    @Override
    public void commonSend(EmailSendReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
    
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByEmail (req.getEmail ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        // 发送邮件
        send (req, CodeTypeEnums.COMMON);
    }
    
    @Override
    public void commonValidate(EmailValidateReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateEmail (req.getEmail ())),
                BizCodeEnum.EMAIL_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
    
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByEmail (req.getEmail ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
        
        // 校验邮箱验证码
        validate (req, CodeTypeEnums.COMMON);
    }
    
    /**
     * 通用方法-发送邮件
     *
     * @param req 请求类型
     * @param type 发送类型
     */
    private void send(EmailSendReq req, CodeTypeEnums type) {
        EmailSendDtoReq dtoReq = BeanUtil.toBean (req, EmailSendDtoReq.class);
        dtoReq.setType (type.getType ());
        emailFeignClient.send (dtoReq);
    }
    
    /**
     * 通用方法-校验邮件验证码
     *
     * @param req 请求类型
     * @param type 发送类型
     */
    private void validate(EmailValidateReq req, CodeTypeEnums type) {
        EmailValidateDtoReq dtoReq = BeanUtil.toBean (req, EmailValidateDtoReq.class);
        dtoReq.setEmailType (type.getType ());
        emailFeignClient.validate (dtoReq);
    }
}
