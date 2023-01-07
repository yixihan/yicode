package com.yixihan.yicode.thirdpart.openapi.web.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.open.api.vo.request.sms.SMSSendReq;

/**
 * 短信模块 服务限流
 *
 * @author yixihan
 * @date 2023/1/7 13:12
 */
public class SMSBlockHandler {
    
    public static JsonResponse<CommonVO<Boolean>> blockHandlerSMS (SMSSendReq req, BlockException e) {
        return JsonResponse.error (
                new CommonVO<> (Boolean.FALSE),
                BizCodeEnum.SENTINEL_FLOW_ERR.getErrorMsg ());
    }
}
