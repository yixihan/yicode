package com.yixihan.yicode.thirdpart.openapi.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 电话校验
 *
 * @author yixihan
 * @date 2022/11/8 9:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("电话校验")
public class SMSValidateDtoReq {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "发送类型")
    private String mobileType;

    @ApiModelProperty(value = "验证码")
    private String code;
}
