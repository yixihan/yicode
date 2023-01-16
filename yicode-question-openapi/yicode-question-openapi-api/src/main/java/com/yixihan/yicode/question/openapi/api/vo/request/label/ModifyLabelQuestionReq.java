package com.yixihan.yicode.question.openapi.api.vo.request.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加问题标签-req
 *
 * @author yixihan
 * @date 2023/1/15 23:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加问题标签-req")
public class ModifyLabelQuestionReq {
    
    @ApiModelProperty(value = "标签 ID")
    private Long labelId;
    
    @ApiModelProperty(value = "问题 ID")
    private Long questionId;
}
