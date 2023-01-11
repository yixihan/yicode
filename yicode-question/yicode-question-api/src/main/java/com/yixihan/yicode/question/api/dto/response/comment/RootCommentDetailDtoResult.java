package com.yixihan.yicode.question.api.dto.response.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 父评论明细-dtoResult
 *
 * @author yixihan
 * @date 2023/1/11 11:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("父评论明细-dtoResult")
public class RootCommentDetailDtoResult {
    
    @ApiModelProperty(value = "父评论 id")
    private Long rootId;
    
    @ApiModelProperty(value = "评论内容 id")
    private Long answerId;
    
    @ApiModelProperty(value = "评论内容类型(QUESTION:问题, NOTE:题解)")
    private String answerType;
    
    @ApiModelProperty(value = "评论者 id")
    private Long userId;
    
    @ApiModelProperty(value = "评论内容")
    private String content;
    
    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;
    
    @ApiModelProperty(value = "回复数")
    private Integer replyCount;
    
    @ApiModelProperty(value = "子评论明细")
    private List<SonCommentDetailDtoResult> sonCommentDetailList;
    
    @ApiModelProperty(value = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
