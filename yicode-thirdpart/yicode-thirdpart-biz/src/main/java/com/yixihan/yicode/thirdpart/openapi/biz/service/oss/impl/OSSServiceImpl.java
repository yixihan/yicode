package com.yixihan.yicode.thirdpart.openapi.biz.service.oss.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.openapi.api.constant.OssConstant;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.OSSPolicyDtoReq;
import com.yixihan.yicode.thirdpart.openapi.api.dto.request.OSSUploadDtoReq;
import com.yixihan.yicode.thirdpart.openapi.biz.service.oss.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
            String fileName = dtoReq.getUserId () + "/" + dtoReq.getFileDir () + "/" + dtoReq.getFileName () + "." + dtoReq.getFileSuffix ();
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

    @Override
    public Map<String, String> policy(OSSPolicyDtoReq dtoReq) {
        // host 的格式为 bucketName.endpoint
        String host = "https://" + ossConstant.getBucketName () + "." + ossConstant.getEndpoint ();
        String dir = dtoReq.getUserId () + "/" + dtoReq.getFileDir ();
        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis () + expireTime * 1000;
            Date expiration = new Date (expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions ();
            policyConds.addConditionItem (PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem (MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy (expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes (StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String (binaryData);
            String postSignature = ossClient.calculatePostSignature (postPolicy);

            respMap = new LinkedHashMap<> ();
            respMap.put ("accessid", ossConstant.getAccessKey ());
            respMap.put ("policy", encodedPolicy);
            respMap.put ("signature", postSignature);
            respMap.put ("dir", dir);
            respMap.put ("host", host);
            respMap.put ("expire", String.valueOf (expireEndTime / 1000));

        } catch (Exception e) {
            log.error (e.getMessage ());
        } finally {
            ossClient.shutdown ();
        }
        log.info ("respMap:" + respMap);
        return respMap;

    }
}
