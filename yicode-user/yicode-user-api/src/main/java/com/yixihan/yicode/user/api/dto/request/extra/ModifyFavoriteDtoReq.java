package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改收藏夹-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 17:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改收藏夹-dtoReq")
public class ModifyFavoriteDtoReq {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "收藏夹 ID")
    private Long favoriteId;

    @ApiModelProperty(value = "收藏夹名")
    private String favoriteName;
    
    @ApiModelProperty(value = "收藏夹封面")
    private String favoriteBg;
    
    @ApiModelProperty(value = "收藏数量")
    private Integer favoriteCount;
}
