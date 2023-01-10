package com.yixihan.yicode.message.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息发送 - dtoReq
 *
 * @author yixihan
 * @date 2023/1/9 17:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("消息发送 - dtoReq")
public class MsgSendDtoReq {
    
    @ApiModelProperty(value = "消息数据, 转为 json 数据传输")
    private String data;
    
    @ApiModelProperty(value = "消息 ID")
    private String messageId;
    
}
