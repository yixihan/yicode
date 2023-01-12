package com.yixihan.yicode.question.api.dto.response.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 问题-dtoResult
 *
 * @author yixihan
 * @date 2023/1/12 9:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("问题-dtoResult")
public class QuestionDtoResult {
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "问题题目")
    private String questionName;
    
    @ApiModelProperty(value = "问题难度")
    private String questionDifficulty;
    
    @ApiModelProperty(value = "问题点赞数")
    private Integer likeCount;
    
    @ApiModelProperty(value = "问题提交数")
    private Integer commitCount;
    
    @ApiModelProperty(value = "问题通过数")
    private Integer successCount;
    
    @ApiModelProperty(value = "通过率")
    private BigDecimal passRate;
    
    @ApiModelProperty(value = "题解数")
    private Integer noteCount;
    
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
