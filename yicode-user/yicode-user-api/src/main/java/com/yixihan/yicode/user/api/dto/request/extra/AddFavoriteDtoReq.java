package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加收藏夹-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加收藏夹-dtoReq")
public class AddFavoriteDtoReq {

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "收藏类型 (0:题, 1:题解)")
    private Integer favoriteType;

    @ApiModelProperty(value = "收藏夹名")
    private String favoriteName;
}
