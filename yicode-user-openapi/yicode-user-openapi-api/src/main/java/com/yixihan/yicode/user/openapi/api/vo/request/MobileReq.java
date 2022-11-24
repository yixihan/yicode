package com.yixihan.yicode.user.openapi.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 手机号请求-req
 *
 * @author yixihan
 * @date 2022/11/21 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("手机号请求-req")
public class MobileReq {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    private String code;
}
