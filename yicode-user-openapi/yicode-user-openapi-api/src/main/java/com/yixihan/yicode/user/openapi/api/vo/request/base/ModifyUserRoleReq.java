package com.yixihan.yicode.user.openapi.api.vo.request.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色修改-req
 *
 * @author yixihan
 * @date 2022/12/21 10:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户角色修改-req")
public class ModifyUserRoleReq {
    
    @ApiModelProperty(value = "角色 ID")
    private Long roleId;
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
}
