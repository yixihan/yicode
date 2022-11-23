package com.yixihan.yicode.thirdpart.biz.service.oss;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.api.dto.request.oss.OSSPolicyDtoReq;
import com.yixihan.yicode.thirdpart.api.dto.request.oss.OSSUploadDtoReq;

import java.util.Map;

/**
 * oss 服务类
 *
 * @author yixihan
 * @date 2022-10-24-15:19
 */
public interface OSSService {

    /**
     * oss 上传文件
     *
     * @param dtoReq
     * @return
     */
    CommonDtoResult<String> uploadFile(OSSUploadDtoReq dtoReq);

    /**
     * 获取上传密钥
     * @param dtoReq
     */
    Map<String, String> policy(OSSPolicyDtoReq dtoReq);
}
