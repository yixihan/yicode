package com.yixihan.yicode.question.openapi.api.vo.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加父评论-req
 *
 * @author yixihan
 * @date 2023/1/13 16:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加父评论-req")
public class AddRootCommentReq {
    
    @ApiModelProperty(value = "评论内容 id")
    private Long answerId;
    
    @ApiModelProperty(value = "评论内容类型(QUESTION:问题, NOTE:题解)")
    private String answerType;
    
    @ApiModelProperty(value = "评论内容")
    private String content;
}
