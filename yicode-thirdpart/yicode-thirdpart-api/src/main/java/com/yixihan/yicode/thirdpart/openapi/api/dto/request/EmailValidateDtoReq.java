package com.yixihan.yicode.thirdpart.openapi.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信验证
 *
 * @author yixihan
 * @date 2022/11/8 9:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("短信验证")
public class EmailValidateDtoReq {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "发送类型")
    private String emailType;

    @ApiModelProperty(value = "验证码")
    private String code;
}
