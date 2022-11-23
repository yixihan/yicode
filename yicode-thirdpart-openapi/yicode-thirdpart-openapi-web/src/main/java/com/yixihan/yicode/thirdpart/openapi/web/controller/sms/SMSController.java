package com.yixihan.yicode.thirdpart.openapi.web.controller.sms;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.open.api.reset.sms.SMSOpenApi;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSSendReq;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSValidateReq;
import com.yixihan.yicode.thirdpart.openapi.biz.service.sms.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短信模块 前端控制器
 *
 * @author yixihan
 * @date 2022/11/23 14:44
 */
@Slf4j
@RestController
public class SMSController implements SMSOpenApi {
    
    @Resource
    private SMSService smsService;
    
    @Override
    public JsonResponse<CommonVO<Boolean>> loginSend(SMSSendReq req) {
        return JsonResponse.ok (smsService.loginSend (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> loginValidate(SMSValidateReq req) {
        return JsonResponse.ok (smsService.loginValidate (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> registerSend(SMSSendReq req) {
        return JsonResponse.ok (smsService.registerSend (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> registerValidate(SMSValidateReq req) {
        return JsonResponse.ok (smsService.registerValidate (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> resetSend(SMSSendReq req) {
        return JsonResponse.ok (smsService.resetSend (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> resetValidate(SMSValidateReq req) {
        return JsonResponse.ok (smsService.resetValidate (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> commonSend(SMSSendReq req) {
        return JsonResponse.ok (smsService.commonSend (req));
    }

    @Override
    public JsonResponse<CommonVO<Boolean>> commonValidate(SMSValidateReq req) {
        return JsonResponse.ok (smsService.commonValidate (req));
    }
}
