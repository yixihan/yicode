package com.yixihan.yicode.runcode.api.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 问题-dtoResult
 *
 * @author yixihan
 * @date 2022/11/14 10:25
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

    @ApiModelProperty(value = "问题描述")
    private String questionDesc;

    @ApiModelProperty(value = "问题难度")
    private String questionDifficulty;

    @ApiModelProperty(value = "问题点赞数")
    private Integer likeCount;

    @ApiModelProperty(value = "问题提交数")
    private Integer commitCount;

    @ApiModelProperty(value = "问题通过数")
    private Integer successCount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
