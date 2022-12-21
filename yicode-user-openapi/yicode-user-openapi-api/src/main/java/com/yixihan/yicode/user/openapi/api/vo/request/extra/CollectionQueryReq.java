package com.yixihan.yicode.user.openapi.api.vo.request.extra;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 查询收藏内容-req
 *
 * @author yixihan
 * @date 2022/12/21 10:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询收藏内容-req")
public class CollectionQueryReq extends PageReq {
    
    @ApiModelProperty(value = "收藏夹 id")
    private Long favoriteId;
}
