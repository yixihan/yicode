package com.yixihan.yicode.thirdpart.openapi.biz.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.yixihan.yicode.thirdpart.openapi.biz.constant.OssConstant;
import com.yixihan.yicode.thirdpart.openapi.biz.service.TestUpLoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * oss - 测试服务类
 *
 * @author yixihan
 * @date 2022-10-24-15:27
 */
@Slf4j
@Service
public class TestUpLoadServiceImpl implements TestUpLoadService {

    @Resource
    private OSS ossClient;

    @Resource
    private OssConstant ossConstant;

    @Override
    public String uploadFile(MultipartFile file) {
        PutObjectRequest request;
        try {
            request = new PutObjectRequest (ossConstant.getBucketName (), file.getOriginalFilename (), file.getInputStream ());
            PutObjectResult result = ossClient.putObject (request);
            log.info ("result : {}", result.toString ());

            return JSONUtil.toJsonStr (result);
        } catch (IOException e) {
            log.error ("文件上传失败 : {}", e.getMessage (), new RuntimeException (e));
        }

        return "文件上传失败";
    }
}
