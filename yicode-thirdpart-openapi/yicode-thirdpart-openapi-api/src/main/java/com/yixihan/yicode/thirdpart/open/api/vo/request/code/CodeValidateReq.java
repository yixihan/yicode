package com.yixihan.yicode.thirdpart.open.api.vo.request.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片验证码校验-req
 *
 * @author yixihan
 * @date 2022/11/23 14:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("图片验证码校验-req")
public class CodeValidateReq {

    @ApiModelProperty(value = "uuid")
    private String uuid;

    @ApiModelProperty(value = "验证码")
    private String code;
}
