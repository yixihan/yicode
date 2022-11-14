package com.yixihan.yicode.thirdpart.openapi.web.fallback;

import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.SMSSendDtoReq;

/**
 * sms sentinel fallback
 *
 * @author yixihan
 * @date 2022-10-28-14:12
 */
public class SMSFallback {

    public ApiResult<String> smsBlockHandler (SMSSendDtoReq dtoReq) {
        return ApiResult.create (BizCodeEnum.SENTINEL_FLOW_ERR.getErrorMsg ());
    }
}
