package com.yixihan.yicode.user.api.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author yixihan
 * @date 2022-10-22-18:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User对象", description = "用户表")
public class UserDtoResult {

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户手机号")
    private String userMobile;

    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;

    @ApiModelProperty(value = "逻辑删除")
    private Integer isDeleted;



}
