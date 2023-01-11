package com.yixihan.yicode.question.api.dto.response.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签-dtoResult
 *
 * @author yixihan
 * @date 2023/1/11 14:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("标签-dtoResult")
public class LabelDtoResult {
    
    @ApiModelProperty(value = "标签 id")
    private Long labelId;
    
    @ApiModelProperty(value = "标签名")
    private String labelName;
}
