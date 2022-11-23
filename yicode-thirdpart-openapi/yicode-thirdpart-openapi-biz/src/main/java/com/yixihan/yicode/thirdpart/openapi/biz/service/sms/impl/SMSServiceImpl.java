package com.yixihan.yicode.thirdpart.openapi.biz.service.sms.impl;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.feign.thirdpart.sms.SMSFeignClient;
import com.yixihan.yicode.thirdpart.openapi.biz.service.sms.SMSService;
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

    @Override
    public CommonVO<Boolean> loginSend(SMSSendReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> loginValidate(SMSValidateReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> registerSend(SMSSendReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> registerValidate(SMSValidateReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> resetSend(SMSSendReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> resetValidate(SMSValidateReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> commonSend(SMSSendReq req) {
        return null;
    }

    @Override
    public CommonVO<Boolean> commonValidate(SMSValidateReq req) {
        return null;
    }
}
