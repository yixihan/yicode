package com.yixihan.yicode.thirdpart.openapi.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yixihan
 * @date 2022-10-24-20:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮件发送请求实体类")
public class SendEmailDtoReq {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "发送类型")
    private Integer emailType;
}
