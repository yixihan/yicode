package com.yixihan.yicode.thirdpart.openapi.biz.service.oss;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.thirdpart.open.api.vo.request.oss.OSSUploadReq;

import java.util.Map;

/**
 * oss 模块 服务类
 *
 * @author yixihan
 * @date 2022/11/23 14:53
 */
public interface OSSService {

    /**
     * 上传文件
     *
     * @param req 请求参数
     */
    CommonVO<String> uploadFile(OSSUploadReq req);

    /**
     * 获取上传密钥
     */
    Map<String, String> policy();

}
