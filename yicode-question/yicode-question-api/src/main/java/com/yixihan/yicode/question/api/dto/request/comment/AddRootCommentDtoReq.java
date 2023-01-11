package com.yixihan.yicode.question.api.dto.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加父评论-dtoReq
 *
 * @author yixihan
 * @date 2023/1/11 10:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加父评论-dtoReq")
public class AddRootCommentDtoReq {
    
    @ApiModelProperty(value = "评论内容 id")
    private Long answerId;
    
    @ApiModelProperty(value = "评论内容类型(QUESTION:问题, NOTE:题解)")
    private String answerType;
    
    @ApiModelProperty(value = "评论者 id")
    private Long userId;
    
    @ApiModelProperty(value = "评论内容")
    private String content;
}
