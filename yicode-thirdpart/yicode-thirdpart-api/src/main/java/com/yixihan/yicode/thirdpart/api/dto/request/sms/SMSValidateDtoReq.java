package com.yixihan.yicode.thirdpart.api.dto.request.sms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信验证码校验-dtoReq
 *
 * @author yixihan
 * @date 2022/11/8 9:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("短信验证码校验-dtoReq")
public class SMSValidateDtoReq {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "发送类型")
    private String mobileType;

    @ApiModelProperty(value = "验证码")
    private String code;
}
