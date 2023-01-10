package com.yixihan.yicode.user.openapi.api.vo.request.msg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 阅读消息-req
 *
 * @author yixihan
 * @date 2023/1/10 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("阅读消息-req")
public class ReadMessageReq {
    
    @ApiModelProperty(value = "消息 ID")
    private List<Long> messageIdList;
}
