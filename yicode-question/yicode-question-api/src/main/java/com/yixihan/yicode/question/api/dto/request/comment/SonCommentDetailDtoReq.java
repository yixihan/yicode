package com.yixihan.yicode.question.api.dto.request.comment;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 子评论明细-dtoReq
 *
 * @author yixihan
 * @date 2023/1/11 11:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("子评论明细-dtoReq")
public class SonCommentDetailDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "父评论 id")
    private Long rootId;
}
