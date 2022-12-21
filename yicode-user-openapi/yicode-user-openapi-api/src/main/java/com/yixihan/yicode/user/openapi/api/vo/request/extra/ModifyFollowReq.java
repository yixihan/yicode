package com.yixihan.yicode.user.openapi.api.vo.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改关注者-req
 *
 * @author yixihan
 * @date 2022/12/21 13:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改关注者-req")
public class ModifyFollowReq {
    
    @ApiModelProperty(value = "关注人用户 id")
    private Long followUserId;
}
