package com.yixihan.yicode.user.openapi.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 重置密码-req
 *
 * @author yixihan
 * @date 2022/11/9 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("重置密码-req")
public class ResetPasswordReq {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "新密码")
    private String newPassword;

    @ApiModelProperty(value = "重置密码方式")
    private String type;
}
