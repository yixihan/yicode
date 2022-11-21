package com.yixihan.yicode.thirdpart.openapi.biz.service;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.CodeValidateDtoReq;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码服务类
 *
 * @author yixihan
 * @date 2022-10-27-22:10
 */
public interface CodeService {

    /**
     * 验证码生成
     *
     * @param keyName redis 缓存key
     * @return
     */
    String getCode (String keyName);

    /**
     * 验证码校验
     *
     * @param keyName redis 缓存 key
     * @param code 验证码
     * @return
     */
    CommonDtoResult<Boolean> validate (String keyName, String code);

    /**
     * 生产图片验证码
     * @param response response
     * @param uuid 随机 ID
     */
    void createCode(HttpServletResponse response, String uuid) throws IOException;

    /**
     * 校验图片验证码
     * @param dtoReq
     * @return
     */
    CommonDtoResult<Boolean> validateCode(CodeValidateDtoReq dtoReq);
}
