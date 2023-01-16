package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 校验收藏夹类型-dtoReq
 *
 * @author yixihan
 * @date 2023/1/16 17:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("校验收藏夹类型-dtoReq")
public class VerifyFavoriteTypeDtoReq {
    
    @ApiModelProperty(value = "收藏夹 ID")
    private Long favoriteId;
    
    @ApiModelProperty(value = "收藏夹 类型")
    private String favoriteType;
}
