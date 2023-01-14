package com.yixihan.yicode.question.openapi.api.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点赞-req
 *
 * @author yixihan
 * @date 2023/1/13 17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("点赞-req")
public class LikeReq {
    
    @ApiModelProperty(value = "点赞内容 ID")
    private Long sourceId;
}
