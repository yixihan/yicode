package com.yixihan.yicode.thirdpart.biz.service.oss;

import com.yixihan.yicode.thirdpart.api.dto.request.oss.OSSPolicyDtoReq;

import java.util.Map;

/**
 * oss 服务类
 *
 * @author yixihan
 * @date 2022-10-24-15:19
 */
public interface OSSService {

    /**
     * 获取上传密钥
     *
     * @param dtoReq 请求参数
     * @return 上传密钥
     */
    Map<String, String> policy(OSSPolicyDtoReq dtoReq);
}
