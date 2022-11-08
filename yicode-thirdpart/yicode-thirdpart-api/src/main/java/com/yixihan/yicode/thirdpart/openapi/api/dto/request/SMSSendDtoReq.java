package com.yixihan.yicode.thirdpart.openapi.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yixihan
 * @date 2022-10-27-15:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("手机短信发送请求参数")
public class SMSSendDtoReq {

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("发送类型")
    private String type;

}
