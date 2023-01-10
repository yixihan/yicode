package com.yixihan.yicode.user.api.dto.request.msg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 消息明细-dtoReq
 *
 * @author yixihan
 * @date 2023/1/10 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("阅读消息-dtoReq")
public class ReadMessageDtoReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "消息 ID")
    private List<Long> messageIdList;
}
