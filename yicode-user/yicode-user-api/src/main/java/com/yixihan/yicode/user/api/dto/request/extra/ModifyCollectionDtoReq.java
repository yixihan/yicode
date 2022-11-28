package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 修改收藏内容-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 17:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("修改收藏内容-dtoReq")
public class ModifyCollectionDtoReq {

    @ApiModelProperty(value = "收藏夹 ID")
    private Long favoriteId;


    @ApiModelProperty(value = "收藏内容 ID")
    private Long collectionId;
}
