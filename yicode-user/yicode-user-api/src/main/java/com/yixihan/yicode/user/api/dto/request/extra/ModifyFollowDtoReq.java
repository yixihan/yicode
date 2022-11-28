package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改关注者-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 16:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改关注者-dtoReq")
public class ModifyFollowDtoReq {

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "关注人用户 id")
    private Long followUserId;
}
