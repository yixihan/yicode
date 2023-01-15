package com.yixihan.yicode.question.openapi.api.vo.response.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签-标签-vo
 *
 * @author yixihan
 * @date 2023/1/11 14:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("标签-vo")
public class LabelVO {
    
    @ApiModelProperty(value = "标签 id")
    private Long labelId;
    
    @ApiModelProperty(value = "标签名")
    private String labelName;
}
