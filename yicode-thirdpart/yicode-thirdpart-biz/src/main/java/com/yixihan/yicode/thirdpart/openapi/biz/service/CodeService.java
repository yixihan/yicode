package com.yixihan.yicode.thirdpart.openapi.biz.service;

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
    Boolean validate (String keyName, String code);
}
