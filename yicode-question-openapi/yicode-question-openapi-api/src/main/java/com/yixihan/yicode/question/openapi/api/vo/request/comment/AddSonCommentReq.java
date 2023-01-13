package com.yixihan.yicode.question.openapi.api.vo.request.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加子评论-req
 *
 * @author yixihan
 * @date 2023/1/13 16:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加子评论-req")
public class AddSonCommentReq {
    
    @ApiModelProperty(value = "父评论 id")
    private Long rootId;
    
    @ApiModelProperty(value = "回复用户 id")
    private Long replyUserId;
    
    @ApiModelProperty(value = "评论内容")
    private String content;
}
