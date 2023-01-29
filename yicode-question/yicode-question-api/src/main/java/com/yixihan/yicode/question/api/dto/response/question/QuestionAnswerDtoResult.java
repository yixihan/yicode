package com.yixihan.yicode.question.api.dto.response.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 获取问题答案-dtoResult
 *
 * @author yixihan
 * @date 2023/1/12 10:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获取问题答案-dtoResult")
public class QuestionAnswerDtoResult {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "问题名称")
    private String questionName;
    
    @ApiModelProperty(value = "问题难度")
    private String questionDifficulty;
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "代码语言类型")
    private String answerType;
    
    @ApiModelProperty(value = "代码")
    private String answerCode;
    
    @ApiModelProperty(value = "代码运行结果(acm模式运行结果)")
    private String answerFlag;
    
    @ApiModelProperty(value = "时间消耗")
    private Integer answerTimeConsume;
    
    @ApiModelProperty(value = "内存消耗")
    private Integer answerMemoryConsume;
    
    @ApiModelProperty(value = "备注")
    private String answerNote;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
