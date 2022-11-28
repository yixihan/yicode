package com.yixihan.yicode.user.api.dto.request.extra;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 收藏夹内容获取-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 15:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("收藏夹内容获取-dtoReq")
public class CollectionQueryDtoReq extends PageDtoReq {

    @ApiModelProperty(value = "收藏夹 id")
    private Long favoriteId;
}

