package com.yixihan.yicode.thirdpart.open.api.vo.request.sms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信验证码校验-req
 *
 * @author yixihan
 * @date 2022/11/23 13:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("短信验证码校验-req")
public class SMSValidateReq {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    private String code;
}
