package com.yixihan.yicode.user.openapi.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通过用户名+密码方式注册
 *
 * @author yixihan
 * @date 2022/11/9 9:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("通过用户名+密码方式注册")
public class RegisterUserReq {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话号码")
    private String mobile;

    @ApiModelProperty(value = "注册方式")
    private String type;
}
