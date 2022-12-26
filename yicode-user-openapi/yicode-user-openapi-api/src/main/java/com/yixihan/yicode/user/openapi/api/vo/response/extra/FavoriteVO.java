package com.yixihan.yicode.user.openapi.api.vo.response.extra;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 收藏夹-vo
 *
 * @author yixihan
 * @date 2022/12/21 10:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("收藏夹-vo")
public class FavoriteVO {
    
    @ApiModelProperty(value = "收藏夹 id")
    private Long favoriteId;
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "用户名")
    private String userName;
    
    @ApiModelProperty(value = "收藏类型 (0:题, 1:题解)")
    private String favoriteType;
    
    @ApiModelProperty(value = "收藏夹名")
    private String favoriteName;
    
    @ApiModelProperty(value = "收藏数量")
    private Integer favoriteCount;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
}
