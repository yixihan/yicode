package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收藏夹详情获取-dtoReq
 *
 * @author yixihan
 * @date 2022/12/21 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("收藏夹详情获取-dtoReq")
public class FavoriteDetailQueryDtoReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "收藏夹 ID")
    private Long favoriteId;
}
