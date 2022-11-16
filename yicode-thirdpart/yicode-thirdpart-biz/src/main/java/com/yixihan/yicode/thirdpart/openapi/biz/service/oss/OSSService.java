package com.yixihan.yicode.thirdpart.openapi.biz.service.oss;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.OSSUploadDtoReq;

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
}
