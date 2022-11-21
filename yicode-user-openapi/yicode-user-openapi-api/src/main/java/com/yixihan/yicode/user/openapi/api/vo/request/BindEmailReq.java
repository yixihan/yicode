package com.yixihan.yicode.user.openapi.api.vo.request;

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
public class BindEmailReq {

    @ApiModelProperty(value = "邮箱")
    private String email;
}
