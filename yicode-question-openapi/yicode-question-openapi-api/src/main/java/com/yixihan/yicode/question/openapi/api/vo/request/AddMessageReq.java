package com.yixihan.yicode.question.openapi.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新增消息-req
 *
 * @author yixihan
 * @date 2023/1/10 14:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("新增消息-req")
public class AddMessageReq {
    
    @ApiModelProperty(value = "发送消息对应id (comment_root, comment_reply, note)")
    private Long sourceId;
    
    @ApiModelProperty(value = "消息类型")
    private String messageType;
    
    @ApiModelProperty(value = "接收消息用户 ID")
    private Long receiveUseId;
    
}
