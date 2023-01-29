package com.yixihan.yicode.question.openapi.web.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;

/**
 * 代码运行 服务限流
 *
 * @author yixihan
 * @date 2023/1/29 15:25
 */
public class CodeRunBlockHandler {
    
    public static JsonResponse<CommonVO<Boolean>> blockHandlerTest (CodeReq req, BlockException e) {
        return JsonResponse.error (
                new CommonVO<> (Boolean.FALSE),
                BizCodeEnum.SENTINEL_FLOW_ERR.getErrorMsg ());
    }
    
    public static JsonResponse<CommonVO<Boolean>> blockHandlerCommit (CodeReq req, BlockException e) {
        return JsonResponse.error (
                new CommonVO<> (Boolean.FALSE),
                BizCodeEnum.SENTINEL_FLOW_ERR.getErrorMsg ());
    }
}
