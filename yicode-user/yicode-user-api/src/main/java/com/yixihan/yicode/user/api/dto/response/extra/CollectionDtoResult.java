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
 * 收藏夹内容获取-dtoResult
 *
 * @author yixihan
 * @date 2022/12/19 10:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("收藏夹内容获取-dtoResult")
public class CollectionDtoResult {
    
    @ApiModelProperty(value = "收藏夹 id")
    private Long favoriteId;
    
    @ApiModelProperty(value = "用户收藏内容 id")
    private Long collectionId;
    
    @ApiModelProperty(value = "收藏时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
