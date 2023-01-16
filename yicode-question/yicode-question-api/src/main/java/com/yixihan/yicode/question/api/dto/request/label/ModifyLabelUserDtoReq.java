package com.yixihan.yicode.question.api.dto.request.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加用户标签-dtoReq
 *
 * @author yixihan
 * @date 2023/1/15 23:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加用户标签-dtoReq")
public class ModifyLabelUserDtoReq {
    
    @ApiModelProperty(value = "标签 ID")
    private String labelId;
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
}
