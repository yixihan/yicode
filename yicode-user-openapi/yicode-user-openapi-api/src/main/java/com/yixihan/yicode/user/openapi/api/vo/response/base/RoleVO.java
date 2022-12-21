package com.yixihan.yicode.user.openapi.api.vo.response.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色-vo
 *
 * @author yixihan
 * @date 2022-10-22-17:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色-vo")
public class RoleVO {

    @ApiModelProperty(value = "角色 id")
    private Long roleId;

    @ApiModelProperty(value = "角色名")
    private String roleName;

}
