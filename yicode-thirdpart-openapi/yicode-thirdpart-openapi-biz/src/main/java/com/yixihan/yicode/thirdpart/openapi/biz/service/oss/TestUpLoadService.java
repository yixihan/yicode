package com.yixihan.yicode.thirdpart.openapi.biz.service.oss;

import org.springframework.web.multipart.MultipartFile;

/**
 * oss - 测试服务类
 *
 * @author yixihan
 * @date 2022-10-24-15:19
 */
public interface TestUpLoadService {

    /**
     * 测试 oss 上传文件
     *
     * @param file 文件
     * @return
     */
    String uploadFile(MultipartFile file);
}
