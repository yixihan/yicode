package com.yixihan.yicode.user.openapi.api.vo.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 关注者查询-req
 *
 * @author yixihan
 * @date 2022/12/21 13:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("关注者查询-req")
public class FollowQueryReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
}
