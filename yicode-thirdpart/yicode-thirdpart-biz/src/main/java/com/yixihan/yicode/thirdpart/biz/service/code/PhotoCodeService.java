package com.yixihan.yicode.thirdpart.biz.service.code;

import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;

/**
 * 图片验证码 服务类
 *
 * @author yixihan
 * @date 2022/12/26 14:35
 */
public interface PhotoCodeService {
    
    
    /**
     * 生产图片验证码
     *
     * @param code 验证码
     * @param uuid 随机 ID
     */
    void create(String code, String uuid);
    
    /**
     * 校验图片验证码
     *
     * @param dtoReq 请求参数
     */
    void validate(CodeValidateDtoReq dtoReq);
}
