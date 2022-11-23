package com.yixihan.yicode.thirdpart.openapi.biz.service.email.impl;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
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
        return null;
    }

    @Override
    public CommonVO<Boolean> loginValidate(EmailValidateReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> registerSend(EmailSendReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> registerValidate(EmailValidateReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> resetSend(EmailSendReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> resetValidate(EmailValidateReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> commonSend(EmailSendReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> commonValidate(EmailValidateReq req) {
        return null;
    }
}
