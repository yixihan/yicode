package com.yixihan.yicode.thirdpart.biz.service.code;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;

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
     * 将验证码存入 redis 中， 并设置有效时间
     *
     * @param keyName redis key
     * @param code 验证码
     */
    void addRedis(String keyName, String code);
    
}
