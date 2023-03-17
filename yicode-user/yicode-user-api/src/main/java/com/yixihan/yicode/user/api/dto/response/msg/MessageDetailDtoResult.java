package com.yixihan.yicode.user.api.dto.response.msg;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 消息明细-dtoResult
 *
 * @author yixihan
 * @date 2023/1/10 14:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("消息明细-dtoResult")
public class MessageDetailDtoResult {
    
    @ApiModelProperty(value = "发送者 id")
    private Long sendUserId;
    
    @ApiModelProperty(value = "发送者用户名")
    private String sendUserName;
    
    @ApiModelProperty(value = "接收者 id")
    private Long receiveUseId;
    
    @ApiModelProperty(value = "消息内容")
    private String msg;
    
    @ApiModelProperty(value = "发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
    @ApiModelProperty(value = "乐观锁")
    private Integer version;
    
    @ApiModelProperty(value = "逻辑删除")
    private Integer delFlag;
}
