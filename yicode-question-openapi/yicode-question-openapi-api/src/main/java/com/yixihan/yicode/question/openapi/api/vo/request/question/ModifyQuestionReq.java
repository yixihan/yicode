package com.yixihan.yicode.question.openapi.api.vo.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改问题-dtoReq
 *
 * @author yixihan
 * @date 2023/1/12 9:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改问题-dtoReq")
public class ModifyQuestionReq {
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "问题题目")
    private String questionName;
    
    @ApiModelProperty(value = "问题描述")
    private String questionDesc;
    
    @ApiModelProperty(value = "问题难度")
    private String questionDifficulty;
}
