package com.yixihan.yicode.question.openapi.api.vo.response.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 子评论明细-vo
 *
 * @author yixihan
 * @date 2023/1/13 17:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("子评论明细-vo")
public class SonCommentDetailVO {
    
    @ApiModelProperty(value = "子评论 id")
    private Long replyId;
    
    @ApiModelProperty(value = "父评论 id")
    private Long rootId;
    
    @ApiModelProperty(value = "评论者 id")
    private Long userId;
    
    @ApiModelProperty(value = "评论者 用户名")
    private Long userName;
    
    @ApiModelProperty(value = "回复用户 id")
    private Long replyUserId;
    
    @ApiModelProperty(value = "回复用户 用户名")
    private Long replyUserName;
    
    @ApiModelProperty(value = "评论内容")
    private String content;
    
    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;
    
    @ApiModelProperty(value = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
