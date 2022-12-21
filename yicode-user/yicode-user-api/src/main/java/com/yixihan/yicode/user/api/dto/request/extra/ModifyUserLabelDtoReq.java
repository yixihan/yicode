package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新用户标签-dtoReq
 *
 * @author yixihan
 * @date 2022/12/21 16:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("更新用户标签-dtoReq")
public class ModifyUserLabelDtoReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "标签 ID")
    private Long labelId;
}
