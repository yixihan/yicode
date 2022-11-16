package com.yixihan.yicode.thirdpart.openapi.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传-dtoReq
 *
 * @author yixihan
 * @date 2022/11/16 13:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文件上传-dtoReq")
public class OSSUploadDtoReq {

    @ApiModelProperty(value = "文件")
    private MultipartFile file;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "文件后缀")
    private String fileSuffix;

    @ApiModelProperty(value = "文件上传目录")
    private String fileDir;

    @ApiModelProperty(value = "用户 ID")
    private Long userId;

}
