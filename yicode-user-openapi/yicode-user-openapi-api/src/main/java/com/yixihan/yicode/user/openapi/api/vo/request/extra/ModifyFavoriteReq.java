package com.yixihan.yicode.user.openapi.api.vo.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改收藏夹-req
 *
 * @author yixihan
 * @date 2022/12/21 10:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改收藏夹-req")
public class ModifyFavoriteReq {
    
    @ApiModelProperty(value = "收藏夹 ID")
    private Long favoriteId;
    
    @ApiModelProperty(value = "收藏夹名")
    private String favoriteName;
    
    @ApiModelProperty(value = "收藏夹封面")
    private String favoriteBg;
}
