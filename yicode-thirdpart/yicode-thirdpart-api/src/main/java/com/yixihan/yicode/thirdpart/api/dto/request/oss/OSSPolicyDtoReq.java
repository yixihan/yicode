package com.yixihan.yicode.thirdpart.api.dto.request.oss;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传密钥获取-dtoReq
 *
 * @author yixihan
 * @date 2022/11/16 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文件上传密钥获取-dtoReq")
public class OSSPolicyDtoReq {

    @ApiModelProperty(value = "文件上传目录")
    private String fileDir;

    @ApiModelProperty(value = "用户 ID")
    private Long userId;
}
