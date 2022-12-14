package com.yixihan.yicode.thirdpart.api.prop.oss;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * oss 配置参数
 *
 * @author yixihan
 * @date 2022-10-24-15:29
 */
@Data
@Component
public class OssProp {

    @Value("${yicode.alicloud.oss.access-key}")
    private String accessKey;

    @Value("${yicode.alicloud.oss.secret-key}")
    private String secretKey;

    @Value("${yicode.alicloud.oss.endpoint}")
    private String endpoint;

    @Value ("${yicode.alicloud.oss.bucket-name}")
    private String bucketName;

    @Value ("${yicode.alicloud.oss.host}")
    private String host;
}
