package com.yixihan.yicode.question.api.dto.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取评论数量-dtoReq
 *
 * @author yixihan
 * @date 2023/1/15 22:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获取评论数量-dtoReq")
public class QuestionCommentCountDtoReq {
    
    @ApiModelProperty(value = "评论内容 id")
    private Long answerId;
    
    @ApiModelProperty(value = "评论内容类型(QUESTION:问题, NOTE:题解)")
    private String answerType;
}
