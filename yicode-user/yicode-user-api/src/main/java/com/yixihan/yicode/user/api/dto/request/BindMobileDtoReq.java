package com.yixihan.yicode.user.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 手机号绑定-req
 *
 * @author yixihan
 * @date 2022/11/21 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("手机号绑定-req")
public class BindMobileDtoReq {

    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    @ApiModelProperty(value = "手机号")
    private String mobile;
}
