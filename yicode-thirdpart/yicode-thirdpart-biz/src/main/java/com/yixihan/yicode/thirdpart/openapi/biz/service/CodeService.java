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
}
