package com.yixihan.yicode.thirdpart.openapi.biz.service.oss;

import java.util.Map;

/**
 * oss 模块 服务类
 *
 * @author yixihan
 * @date 2022/11/23 14:53
 */
public interface OSSService {

    /**
     * 获取上传密钥
     *
     * @return 上传密钥
     */
    Map<String, String> policy();
}
