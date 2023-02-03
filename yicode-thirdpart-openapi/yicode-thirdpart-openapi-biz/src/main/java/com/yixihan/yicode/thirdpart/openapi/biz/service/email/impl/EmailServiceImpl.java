package com.yixihan.yicode.thirdpart.openapi.biz.service.email.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.ValidationUtils;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.email.EmailValidateDtoReq;
import com.yixihan.yicode.common.enums.thirdpart.code.CodeTypeEnums;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.email.EmailFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.email.EmailService;
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

    @Override
    public CommonVO<Boolean> loginSend(EmailSendReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailSendDtoReq dtoReq = BeanUtil.toBean (req, EmailSendDtoReq.class);
        dtoReq.setType (CodeTypeEnums.LOGIN.getType ());
        CommonDtoResult<Boolean> dtoResult = emailFeignClient.sendEmail (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> loginValidate(EmailValidateReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailValidateDtoReq dtoReq = BeanUtil.toBean (req, EmailValidateDtoReq.class);
        dtoReq.setEmailType (CodeTypeEnums.LOGIN.getType ());
        CommonDtoResult<Boolean> dtoResult = emailFeignClient.validate (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> registerSend(EmailSendReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailSendDtoReq dtoReq = BeanUtil.toBean (req, EmailSendDtoReq.class);
        dtoReq.setType (CodeTypeEnums.REGISTER.getType ());
        CommonDtoResult<Boolean> dtoResult = emailFeignClient.sendEmail (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> registerValidate(EmailValidateReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailValidateDtoReq dtoReq = BeanUtil.toBean (req, EmailValidateDtoReq.class);
        dtoReq.setEmailType (CodeTypeEnums.REGISTER.getType ());
        CommonDtoResult<Boolean> dtoResult = emailFeignClient.validate (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> resetSend(EmailSendReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailSendDtoReq dtoReq = BeanUtil.toBean (req, EmailSendDtoReq.class);
        dtoReq.setType (CodeTypeEnums.RESET_PASSWORD.getType ());
        CommonDtoResult<Boolean> dtoResult = emailFeignClient.sendEmail (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> resetValidate(EmailValidateReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailValidateDtoReq dtoReq = BeanUtil.toBean (req, EmailValidateDtoReq.class);
        dtoReq.setEmailType (CodeTypeEnums.RESET_PASSWORD.getType ());
        CommonDtoResult<Boolean> dtoResult = emailFeignClient.validate (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> commonSend(EmailSendReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailSendDtoReq dtoReq = BeanUtil.toBean (req, EmailSendDtoReq.class);
        dtoReq.setType (CodeTypeEnums.COMMON.getType ());
        CommonDtoResult<Boolean> dtoResult = emailFeignClient.sendEmail (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }

    @Override
    public CommonVO<Boolean> commonValidate(EmailValidateReq req) {
        if (!ValidationUtils.validateEmail (req.getEmail ())) {
            throw new BizException ("邮箱不符合规范!");
        }
        EmailValidateDtoReq dtoReq = BeanUtil.toBean (req, EmailValidateDtoReq.class);
        dtoReq.setEmailType (CodeTypeEnums.COMMON.getType ());
        CommonDtoResult<Boolean> dtoResult = emailFeignClient.validate (dtoReq).getResult ();
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
}
