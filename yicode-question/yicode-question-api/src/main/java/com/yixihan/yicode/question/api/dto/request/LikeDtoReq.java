package com.yixihan.yicode.question.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点赞-dtoReq
 *
 * @author yixihan
 * @date 2023/1/11 17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("点赞-dtoReq")
public class LikeDtoReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "点赞内容 ID")
    private Long sourceId;
    
    @ApiModelProperty(value = "点赞数量")
    private Integer likeCount;
}
