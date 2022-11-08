package com.yixihan.yicode.thirdpart.openapi.web.fallback;

import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.EmailSendDtoReq;

/**
 * @author yixihan
 * @date 2022-10-28-14:16
 */
public class EmailFallback {

    public ApiResult<String> emailBlockHandler (EmailSendDtoReq dtoReq) {
        return ApiResult.create (BizCodeEnum.SENTINEL_FLOW_ERR.getErrorMsg ());
    }
}
