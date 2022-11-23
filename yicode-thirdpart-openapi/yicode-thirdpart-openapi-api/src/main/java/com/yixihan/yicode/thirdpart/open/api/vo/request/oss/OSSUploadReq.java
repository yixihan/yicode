package com.yixihan.yicode.thirdpart.open.api.vo.request.oss;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传-req
 *
 * @author yixihan
 * @date 2022/11/23 14:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("文件上传-req")
public class OSSUploadReq {

    @ApiModelProperty(value = "文件")
    private MultipartFile file;
}
