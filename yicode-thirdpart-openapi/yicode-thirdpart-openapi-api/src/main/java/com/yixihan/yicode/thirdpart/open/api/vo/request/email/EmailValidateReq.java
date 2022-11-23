package com.yixihan.yicode.thirdpart.open.api.vo.request.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件验证码校验-req
 *
 * @author yixihan
 * @date 2022/11/23 14:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮件验证码校验-req")
public class EmailValidateReq {

    @ApiModelProperty(value = "手机号")
    private String email;

    @ApiModelProperty(value = "验证码")
    private String code;
}
