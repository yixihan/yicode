package com.yixihan.yicode.question.openapi.api.vo.request.comment;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索父评论-req
 *
 * @author yixihan
 * @date 2023/1/13 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索父评论-req")
public class RootCommentDetailReq extends PageReq {
    
    @ApiModelProperty(value = "评论内容 id")
    private Long answerId;
    
    @ApiModelProperty(value = "评论内容类型(QUESTION:问题, NOTE:题解)")
    private String answerType;
}
