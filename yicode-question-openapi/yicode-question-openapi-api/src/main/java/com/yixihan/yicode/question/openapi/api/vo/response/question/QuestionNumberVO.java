package com.yixihan.yicode.question.openapi.api.vo.response.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目数量情况-vo
 *
 * @author yixihan
 * @date 2023/1/29 16:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("题目数量情况-vo")
public class QuestionNumberVO {
    
    @ApiModelProperty(value = "题目总数")
    private Integer questionCount;
    
    @ApiModelProperty(value = "困难题目总数")
    private Integer hardQuestionCount;
    
    @ApiModelProperty(value = "普通题目总数")
    private Integer mediumQuestionCount;
    
    @ApiModelProperty(value = "简单题目总数")
    private Integer easyQuestionCount;
}
