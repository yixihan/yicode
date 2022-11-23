package com.yixihan.yicode.thirdpart.open.api.vo.request.sms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信发送-req
 *
 * @author yixihan
 * @date 2022/11/23 13:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("短信发送-req")
public class SMSSendReq {

    @ApiModelProperty(value = "手机号")
    private String mobile;
}
