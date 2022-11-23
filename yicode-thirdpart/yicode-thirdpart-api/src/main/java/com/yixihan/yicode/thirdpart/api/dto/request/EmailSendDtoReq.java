package com.yixihan.yicode.thirdpart.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件发送-dtoReq
 *
 * @author yixihan
 * @date 2022-10-24-20:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮件发送-dtoReq")
public class EmailSendDtoReq {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "发送类型")
    private String type;
}
