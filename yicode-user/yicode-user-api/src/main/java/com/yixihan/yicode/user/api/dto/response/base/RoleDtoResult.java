package com.yixihan.yicode.user.api.dto.response.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色-dtoResult
 *
 * @author yixihan
 * @date 2022-10-22-18:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色-dtoResult")
public class RoleDtoResult {

    @ApiModelProperty(value = "角色 id")
    private Long roleId;

    @ApiModelProperty(value = "角色名")
    private String roleName;

}
