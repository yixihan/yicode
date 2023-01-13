package com.yixihan.yicode.user.openapi.api.vo.response.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户信息-vo
 *
 * @author yixihan
 * @date 2022-10-22-17:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户信息-vo")
public class UserVO {

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户手机号")
    private String userMobile;

    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;
    
    @ApiModelProperty(value = "注册方式")
    private String registerType;

}
