package com.yixihan.yicode.thirdpart.api.dto.request.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片验证码校验-dtoReq
 *
 * @author yixihan
 * @date 2022/11/21 17:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("图片验证码校验-dtoReq")
public class CodeValidateDtoReq {

    @ApiModelProperty(value = "uuid")
    private String uuid;

    @ApiModelProperty(value = "验证码")
    private String code;
}
