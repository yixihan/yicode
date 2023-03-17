package com.yixihan.yicode.thirdpart.openapi.biz.service.sms.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.enums.thirdpart.code.CodeTypeEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.ValidationUtils;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.sms.SMSFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.user.user.UserFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.sms.SMSService;
import com.yixihan.yicode.user.api.dto.response.base.UserDtoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 短信模块 服务实现类
 *
 * @author yixihan
 * @date 2022/11/23 14:56
 */
@Slf4j
@Service
public class SMSServiceImpl implements SMSService {

    @Resource
    private SMSFeignClient smsFeignClient;
    
    @Resource
    private UserFeignClient userFeignClient;

    @Override
    public void loginSend(SMSSendReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);

        // 用户校验
        UserDtoResult user = userFeignClient.getUserByMobile (req.getMobile ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
        
        // 发送短信
        send (req, CodeTypeEnums.LOGIN);
    }
    
    @Override
    public void loginValidate(SMSValidateReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
    
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByMobile (req.getMobile ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        // 校验短信验证码
        validate (req, CodeTypeEnums.LOGIN);
    }
    
    @Override
    public void registerSend(SMSSendReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);
    
        // 发送短信
        send (req, CodeTypeEnums.REGISTER);
    }
    
    @Override
    public void registerValidate(SMSValidateReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
    
        // 校验短信验证码
        validate (req, CodeTypeEnums.REGISTER);
    }
    
    @Override
    public void resetSend(SMSSendReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);
    
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByMobile (req.getMobile ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        // 发送短信
        send (req, CodeTypeEnums.RESET_PASSWORD);
    }
    
    @Override
    public void resetValidate(SMSValidateReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
    
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByMobile (req.getMobile ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        // 校验短信验证码
        validate (req, CodeTypeEnums.RESET_PASSWORD);
    }
    
    @Override
    public void commonSend(SMSSendReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);
    
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByMobile (req.getMobile ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        // 发送短信
        send (req, CodeTypeEnums.COMMON);
    }
    
    @Override
    public void commonValidate(SMSValidateReq req) {
        // 参数校验
        Assert.isTrue (Boolean.TRUE.equals (ValidationUtils.validateMobile (req.getMobile ())),
                BizCodeEnum.MOBILE_VALIDATE_ERR);
        Assert.isTrue (StrUtil.isNotBlank (req.getCode ()), BizCodeEnum.CODE_EMPTY_ERR);
    
        // 用户校验
        UserDtoResult user = userFeignClient.getUserByMobile (req.getMobile ()).getResult ();
        if (user.getUserId () == null) {
            throw new BizException (BizCodeEnum.ACCOUNT_NOT_FOUND);
        }
    
        // 校验短信验证码
        validate (req, CodeTypeEnums.COMMON);
    }
    
    /**
     * 通用方法-校验短信验证码
     *
     * @param req 请求类型
     * @param type 发送类型
     */
    private void validate(SMSValidateReq req, CodeTypeEnums type) {
        SMSValidateDtoReq dtoReq = BeanUtil.toBean (req, SMSValidateDtoReq.class);
        dtoReq.setMobileType (type.getType ());
        smsFeignClient.validate (dtoReq);
    }
    
    /**
     * 通用方法-发送短信
     *
     * @param req 请求类型
     * @param type 发送类型
     */
    private void send(SMSSendReq req, CodeTypeEnums type) {
        SMSSendDtoReq dtoReq = BeanUtil.toBean (req, SMSSendDtoReq.class);
        dtoReq.setType (type.getType ());
        smsFeignClient.send (dtoReq);
    }
}
