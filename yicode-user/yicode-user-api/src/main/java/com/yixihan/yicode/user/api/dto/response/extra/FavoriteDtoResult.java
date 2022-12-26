package com.yixihan.yicode.user.api.dto.response.extra;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 收藏夹获取-dtoResult
 *
 * @author yixihan
 * @date 2022/11/28 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("收藏夹获取-dtoResult")
public class FavoriteDtoResult {

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
