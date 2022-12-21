package com.yixihan.yicode.user.openapi.api.vo.request.extra;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 收藏夹获取-req
 *
 * @author yixihan
 * @date 2022/12/21 13:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("收藏夹获取-req")
public class FavoriteQueryReq extends PageReq {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
}
