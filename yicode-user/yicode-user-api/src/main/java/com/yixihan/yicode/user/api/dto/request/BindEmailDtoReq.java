package com.yixihan.yicode.user.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮箱绑定-req
 *
 * @author yixihan
 * @date 2022/11/21 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮箱绑定-req")
public class BindEmailDtoReq {

    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
