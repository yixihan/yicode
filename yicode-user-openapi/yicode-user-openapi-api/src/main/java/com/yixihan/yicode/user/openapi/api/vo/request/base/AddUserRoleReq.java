package com.yixihan.yicode.user.openapi.api.vo.request.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户新角色添加-req
 *
 * @author yixihan
 * @date 2022/12/21 10:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户新角色添加-req")
public class AddUserRoleReq {
    
    @ApiModelProperty(value = "角色 ID")
    private Long roleId;
}
