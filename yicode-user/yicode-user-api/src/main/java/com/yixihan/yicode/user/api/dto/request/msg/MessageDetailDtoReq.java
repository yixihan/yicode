package com.yixihan.yicode.user.api.dto.request.msg;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 消息明细-dtoReq
 *
 * @author yixihan
 * @date 2023/1/10 14:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("消息明细-dtoReq")
public class MessageDetailDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
}
