package com.yixihan.yicode.question.openapi.api.vo.request.comment;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 子评论明细-req
 *
 * @author yixihan
 * @date 2023/1/13 17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("子评论明细-req")
public class SonCommentDetailReq extends PageReq {
    
    @ApiModelProperty(value = "父评论 id")
    private Long rootId;
}
