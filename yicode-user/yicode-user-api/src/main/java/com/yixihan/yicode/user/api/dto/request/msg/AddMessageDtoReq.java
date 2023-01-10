package com.yixihan.yicode.user.api.dto.request.msg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新增消息-dtoReq
 *
 * @author yixihan
 * @date 2023/1/10 14:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("新增消息-dtoReq")
public class AddMessageDtoReq {
    
    @ApiModelProperty(value = "消息内容")
    private String msg;
    
    @ApiModelProperty(value = "消息类型")
    private String messageType;
    
    @ApiModelProperty(value = "发送者 ID")
    private Long sendUserId;
    
    @ApiModelProperty(value = "发送者用户名")
    private String sendUserName;
    
    @ApiModelProperty(value = "接收者 ID")
    private Long receiveUseId;
    
}
