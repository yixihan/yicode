package com.yixihan.yicode.user.openapi.api.vo.request.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户名修改-req
 *
 * @author yixihan
 * @date 2022/11/21 20:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户名修改-req")
public class ResetUserNameReq {

    @ApiModelProperty(value = "用户名")
    private String userName;
}
