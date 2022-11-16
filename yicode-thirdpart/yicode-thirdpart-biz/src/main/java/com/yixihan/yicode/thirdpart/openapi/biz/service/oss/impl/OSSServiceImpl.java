package com.yixihan.yicode.thirdpart.openapi.biz.service.oss.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.openapi.api.constant.OssConstant;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.OSSUploadDtoReq;
import com.yixihan.yicode.thirdpart.openapi.biz.service.oss.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * oss 服务类
 *
 * @author yixihan
 * @date 2022-10-24-15:27
 */
@Slf4j
@Service
public class OSSServiceImpl implements OSSService {

    @Resource
    private OSS ossClient;

    @Resource
    private OssConstant ossConstant;

    @Override
    public CommonDtoResult<String> uploadFile(OSSUploadDtoReq dtoReq) {
        PutObjectRequest request;
        try {
            String fileName = dtoReq.getUserId () + "/" + dtoReq.getFileDir () + "/" +
                    dtoReq.getFileName () + "." + dtoReq.getFileSuffix ();
            request = new PutObjectRequest (ossConstant.getBucketName (), fileName, dtoReq.getFile ().getInputStream ());
            PutObjectResult result = ossClient.putObject (request);
            log.info ("result : {}", result.toString ());
            String url = "https://" + ossConstant.getHost () + "/" + fileName;
            return new CommonDtoResult<> (url, "文件上传成功");
        } catch (IOException e) {
            log.error ("文件上传失败 : {}", e.getMessage (), new BizException (BizCodeEnum.OSS_ERR));
        }

        return new CommonDtoResult<> (null, "文件上传失败");
    }
}
