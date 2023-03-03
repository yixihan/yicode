package com.yixihan.yicode.user.openapi.api.vo.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加收藏夹-req
 *
 * @author yixihan
 * @date 2022/12/21 10:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加收藏夹-req")
public class AddFavoriteReq {
    
    @ApiModelProperty(value = "收藏类型(QUESTION : 问题, NOTE : 题解)")
    private String favoriteType;
    
    @ApiModelProperty(value = "收藏夹名")
    private String favoriteName;
    
    @ApiModelProperty(value = "收藏夹封面")
    private String favoriteBg;
}
