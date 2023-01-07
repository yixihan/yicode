package com.yixihan.yicode.thirdpart.openapi.web.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.thirdpart.open.api.vo.request.email.EmailSendReq;

/**
 * 邮箱模块 服务限流
 *
 * @author yixihan
 * @date 2023/1/7 12:12
 */
public class EmailBlockHandler {
    
    public static JsonResponse<CommonVO<Boolean>> blockHandlerEmail (EmailSendReq req, BlockException e) {
        return JsonResponse.error (
                new CommonVO<> (Boolean.FALSE),
                BizCodeEnum.SENTINEL_FLOW_ERR.getErrorMsg ());
    }
}
