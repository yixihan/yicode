package com.yixihan.yicode.thirdpart.biz.service.oss.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.thirdpart.api.dto.request.oss.OSSPolicyDtoReq;
import com.yixihan.yicode.thirdpart.api.prop.oss.OssProp;
import com.yixihan.yicode.thirdpart.biz.service.oss.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * oss 服务实现类
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
    private OssProp ossProp;

    @Override
    public Map<String, String> policy(OSSPolicyDtoReq dtoReq) {
        // host 的格式为 bucketName.endpoint
        String host = "https://" + ossProp.getBucketName () + "." + ossProp.getEndpoint ();
        String dir = dtoReq.getUserId () + "/" + dtoReq.getFileDir ();
        Map<String, String> respMap;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis () + expireTime * 1000;
            Date expiration = new Date (expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConditions = new PolicyConditions ();
            policyConditions.addConditionItem (PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConditions.addConditionItem (MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            // 获取有效密钥
            String postPolicy = ossClient.generatePostPolicy (expiration, policyConditions);
            byte[] binaryData = postPolicy.getBytes (StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String (binaryData);
            String postSignature = ossClient.calculatePostSignature (postPolicy);

            // 填装有效密钥
            respMap = new LinkedHashMap<> ();
            respMap.put ("accessKey", ossProp.getAccessKey ());
            respMap.put ("policy", encodedPolicy);
            respMap.put ("signature", postSignature);
            respMap.put ("dir", dir);
            respMap.put ("host", host);
            respMap.put ("expire", String.valueOf (expireEndTime / 1000));

        } catch (Exception e) {
            log.error ("文件上传模块发生异常 : {}", e.getMessage (), e);
            throw new BizException (BizCodeEnum.OSS_ERR);
        }
        return respMap;

    }
}
