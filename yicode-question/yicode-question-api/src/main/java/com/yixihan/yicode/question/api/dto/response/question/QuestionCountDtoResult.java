package com.yixihan.yicode.question.api.dto.response.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 问题数量-dtoResult
 *
 * @author yixihan
 * @date 2023/3/7 10:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("问题数量-dtoResult")
public class QuestionCountDtoResult {
    
    @ApiModelProperty(value = "题目数量")
    private Integer question;
    
    @ApiModelProperty(value = "困难题目数量")
    private Integer hardQuestion;
    
    @ApiModelProperty(value = "普通题目数量")
    private Integer mediumQuestion;
    
    @ApiModelProperty(value = "简单题目数量")
    private Integer easyQuestion;
}
