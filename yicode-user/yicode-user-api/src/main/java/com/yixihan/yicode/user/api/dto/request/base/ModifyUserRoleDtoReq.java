package com.yixihan.yicode.user.api.dto.request.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改用户角色-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 17:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改用户角色-dtoReq")
public class ModifyUserRoleDtoReq {

    @ApiModelProperty(value = "用户 ID")
    private Long userId;

    @ApiModelProperty(value = "角色 ID")
    private Long roleId;
}
