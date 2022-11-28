package com.yixihan.yicode.user.api.dto.request.extra;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 关注者查询-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 16:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("关注者查询-dtoReq")
public class FollowQueryDtoReq extends PageDtoReq {

    @ApiModelProperty(value = "用户 ID")
    private Long userId;
}
