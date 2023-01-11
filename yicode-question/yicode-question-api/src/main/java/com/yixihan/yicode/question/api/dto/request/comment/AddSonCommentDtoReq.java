package com.yixihan.yicode.question.api.dto.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加子评论-dtoReq
 *
 * @author yixihan
 * @date 2023/1/11 10:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加子评论-dtoReq")
public class AddSonCommentDtoReq {
    
    @ApiModelProperty(value = "父评论 id")
    private Long rootId;
    
    @ApiModelProperty(value = "评论者 id")
    private Long userId;
    
    @ApiModelProperty(value = "回复用户 id")
    private Long replyUserId;
    
    @ApiModelProperty(value = "评论内容")
    private String content;
}
