package com.yixihan.yicode.user.api.dto.request.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册-req
 *
 * @author yixihan
 * @date 2022/11/9 9:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户注册-req")
public class RegisterUserDtoReq {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "注册方式")
    private String type;
}
