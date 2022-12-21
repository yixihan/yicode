package com.yixihan.yicode.user.openapi.api.vo.request.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新角色添加-req
 *
 * @author yixihan
 * @date 2022/12/21 9:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("新角色添加-req")
public class AddRoleReq {
    
    @ApiModelProperty(value = "角色名")
    private String roleName;
    
}
