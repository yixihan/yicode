package com.yixihan.yicode.thirdpart.openapi.api.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * oss常量
 *
 * @author yixihan
 * @date 2022-10-24-15:29
 */
@Data
@Component
public class OssConstant {

    @Value ("${yicode.alicloud.oss.bucket-name}")
    private String bucketName;
}
