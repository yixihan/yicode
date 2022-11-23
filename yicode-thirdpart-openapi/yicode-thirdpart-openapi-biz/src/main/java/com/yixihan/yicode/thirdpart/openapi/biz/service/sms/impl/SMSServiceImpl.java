package com.yixihan.yicode.thirdpart.openapi.biz.service.sms.impl;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSSendDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.sms.SMSValidateDtoReq;
import com.yixihan.yicode.thirdpart.api.enums.code.CodeTypeEnums;
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
        SMSSendDtoReq dtoReq = CopyUtils.copySingle (SMSSendDtoReq.class, req);
        dtoReq.setType (CodeTypeEnums.LOGIN.getType ());
        CommonDtoResult<Boolean> dtoResult = smsFeignClient.send (dtoReq).getResult ();
        return CopyUtils.copySingle (CommonVO.class, dtoResult);
    }

    @Override
    public CommonVO<Boolean> loginValidate(SMSValidateReq req) {
        SMSValidateDtoReq dtoReq = CopyUtils.copySingle (SMSValidateDtoReq.class, req);
        dtoReq.setMobileType (CodeTypeEnums.LOGIN.getType ());
        CommonDtoResult<Boolean> dtoResult = smsFeignClient.validate (dtoReq).getResult ();
        return CopyUtils.copySingle (CommonVO.class, dtoResult);
    }

    @Override
    public CommonVO<Boolean> registerSend(SMSSendReq req) {
        SMSSendDtoReq dtoReq = CopyUtils.copySingle (SMSSendDtoReq.class, req);
        dtoReq.setType (CodeTypeEnums.REGISTER.getType ());
        CommonDtoResult<Boolean> dtoResult = smsFeignClient.send (dtoReq).getResult ();
        return CopyUtils.copySingle (CommonVO.class, dtoResult);
    }

    @Override
    public CommonVO<Boolean> registerValidate(SMSValidateReq req) {
        SMSValidateDtoReq dtoReq = CopyUtils.copySingle (SMSValidateDtoReq.class, req);
        dtoReq.setMobileType (CodeTypeEnums.REGISTER.getType ());
        CommonDtoResult<Boolean> dtoResult = smsFeignClient.validate (dtoReq).getResult ();
        return CopyUtils.copySingle (CommonVO.class, dtoResult);
    }

    @Override
    public CommonVO<Boolean> resetSend(SMSSendReq req) {
        SMSSendDtoReq dtoReq = CopyUtils.copySingle (SMSSendDtoReq.class, req);
        dtoReq.setType (CodeTypeEnums.RESET_PASSWORD.getType ());
        CommonDtoResult<Boolean> dtoResult = smsFeignClient.send (dtoReq).getResult ();
        return CopyUtils.copySingle (CommonVO.class, dtoResult);
    }

    @Override
    public CommonVO<Boolean> resetValidate(SMSValidateReq req) {
        SMSValidateDtoReq dtoReq = CopyUtils.copySingle (SMSValidateDtoReq.class, req);
        dtoReq.setMobileType (CodeTypeEnums.RESET_PASSWORD.getType ());
        CommonDtoResult<Boolean> dtoResult = smsFeignClient.validate (dtoReq).getResult ();
        return CopyUtils.copySingle (CommonVO.class, dtoResult);
    }

    @Override
    public CommonVO<Boolean> commonSend(SMSSendReq req) {
        SMSSendDtoReq dtoReq = CopyUtils.copySingle (SMSSendDtoReq.class, req);
        dtoReq.setType (CodeTypeEnums.COMMON.getType ());
        CommonDtoResult<Boolean> dtoResult = smsFeignClient.send (dtoReq).getResult ();
        return CopyUtils.copySingle (CommonVO.class, dtoResult);
    }

    @Override
    public CommonVO<Boolean> commonValidate(SMSValidateReq req) {
        SMSValidateDtoReq dtoReq = CopyUtils.copySingle (SMSValidateDtoReq.class, req);
        dtoReq.setMobileType (CodeTypeEnums.COMMON.getType ());
        CommonDtoResult<Boolean> dtoResult = smsFeignClient.validate (dtoReq).getResult ();
        return CopyUtils.copySingle (CommonVO.class, dtoResult);
    }
}
