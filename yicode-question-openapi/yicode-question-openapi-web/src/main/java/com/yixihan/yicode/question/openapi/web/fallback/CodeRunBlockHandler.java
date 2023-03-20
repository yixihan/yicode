package com.yixihan.yicode.question.openapi.web.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;

/**
 * 代码运行 服务限流
 *
 * @author yixihan
 * @date 2023/1/29 15:25
 */
public class CodeRunBlockHandler {
    
    public static void blockHandlerTest (CodeReq req, BlockException e) {
        throw new BizException (BizCodeEnum.CODE_FLOW_ERR);
    }
    
    public static void blockHandlerCommit (CodeReq req, BlockException e) {
        throw new BizException (BizCodeEnum.CODE_FLOW_ERR);
    }
}
