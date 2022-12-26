package com.yixihan.yicode.thirdpart.openapi.biz.service.code;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.thirdpart.open.api.vo.request.code.CodeValidateReq;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片验证码 服务类
 *
 * @author yixihan
 * @date 2022/12/26 14:35
 */
public interface PhotoCodeService {
    
    /**
     * 生成图片验证码
     *
     * @param response response
     * @param uuid 随机 ID
     */
    void createCode(HttpServletResponse response, String uuid) throws IOException;
    
    /**
     * 验证图片验证码
     *
     * @param req 请求参数
     */
    CommonVO<Boolean> validateCode(CodeValidateReq req);
}
