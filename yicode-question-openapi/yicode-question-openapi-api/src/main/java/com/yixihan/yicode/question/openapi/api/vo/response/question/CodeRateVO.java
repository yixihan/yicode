package com.yixihan.yicode.question.openapi.api.vo.response.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 用户进度-vo
 *
 * @author yixihan
 * @date 2023/1/28 10:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户进度-vo")
public class CodeRateVO {

    @ApiModelProperty(value = "通过题目数量")
    private Integer acceptedQuestion;
    
    @ApiModelProperty(value = "通过困难题目数量")
    private Integer acceptedHardQuestion;
    
    @ApiModelProperty(value = "通过普通题目数量")
    private Integer acceptedMediumQuestion;
    
    @ApiModelProperty(value = "通过简单题目数量")
    private Integer acceptedEasyQuestion;
    
    @ApiModelProperty(value = "未开始题目数量")
    private Integer unDoQuestion;
    
    @ApiModelProperty(value = "未开始困难题目数量")
    private Integer unDoHardQuestion;
    
    @ApiModelProperty(value = "未开始普通题目数量")
    private Integer unDoMediumQuestion;
    
    @ApiModelProperty(value = "未开始简单题目数量")
    private Integer unDoEasyQuestion;
    
    @ApiModelProperty(value = "未通过题目数量")
    private Integer unAcceptedQuestion;
    
    @ApiModelProperty(value = "题目总数")
    private Integer questionCount;
    
    @ApiModelProperty(value = "困难题目总数")
    private Integer hardQuestionCount;
    
    @ApiModelProperty(value = "普通题目总数")
    private Integer mediumQuestionCount;
    
    @ApiModelProperty(value = "简单题目总数")
    private Integer easyQuestionCount;
    
    @ApiModelProperty(value = "提交总数")
    private Integer commitCount;
    
    @ApiModelProperty(value = "通过提交次数")
    private Integer acceptedCommitCount;
    
    @ApiModelProperty(value = "提交通过率")
    private BigDecimal acceptedCommitRate;
    
    @ApiModelProperty(value = "题目通过率")
    private BigDecimal acceptedQuestionRate;
    
    @ApiModelProperty(value = "困难题目通过率")
    private BigDecimal acceptedHardQuestionRate;
    
    @ApiModelProperty(value = "普通题目通过率")
    private BigDecimal acceptedMediumQuestionRate;
    
    @ApiModelProperty(value = "简单题目通过率")
    private BigDecimal acceptedEasyQuestionRate;
    
}
