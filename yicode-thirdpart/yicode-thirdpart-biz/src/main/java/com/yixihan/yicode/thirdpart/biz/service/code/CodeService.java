package com.yixihan.yicode.thirdpart.biz.service.code;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;

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
     * @return 验证码
     */
    String getCode (String keyName);

    /**
     * 验证码校验
     *
     * @param keyName redis 缓存 key
     * @param code 验证码
     */
    CommonDtoResult<Boolean> validate (String keyName, String code);

    /**
     * 生产图片验证码
     *
     * @param code 验证码
     * @param uuid 随机 ID
     */
    void createCode(String code, String uuid);

    /**
     * 校验图片验证码
     *
     * @param dtoReq 请求参数
     */
    CommonDtoResult<Boolean> validateCode(CodeValidateDtoReq dtoReq);
}
