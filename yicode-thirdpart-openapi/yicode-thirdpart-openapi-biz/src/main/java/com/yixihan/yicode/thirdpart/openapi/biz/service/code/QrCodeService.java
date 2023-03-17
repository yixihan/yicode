package com.yixihan.yicode.thirdpart.openapi.biz.service.code;

import javax.servlet.http.HttpServletResponse;

/**
 * 二维码 服务类
 *
 * @author yixihan
 * @date 2022/12/26 14:40
 */
public interface QrCodeService {
    
    /**
     * 创建二维码
     *
     * @param response 响应体
     * @param param 二维码内容
     */
    void create(HttpServletResponse response, String param);
}
