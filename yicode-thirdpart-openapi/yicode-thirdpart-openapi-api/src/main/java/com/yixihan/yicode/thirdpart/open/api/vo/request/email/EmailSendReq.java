package com.yixihan.yicode.thirdpart.open.api.vo.request.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件发送-req
 *
 * @author yixihan
 * @date 2022/11/23 14:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮件发送-req")
public class EmailSendReq {

    @ApiModelProperty(value = "邮件")
    private String email;
}
