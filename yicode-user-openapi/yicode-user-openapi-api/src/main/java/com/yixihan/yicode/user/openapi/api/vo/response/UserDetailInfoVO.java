package com.yixihan.yicode.user.openapi.api.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yixihan
 * @date 2022-10-22-17:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户信息")
public class UserDetailInfoVO {

    @ApiModelProperty("用户信息")
    private UserVO user;

    @ApiModelProperty("用户权限信息")
    private List<RoleVO> userRoleList;

    @ApiModelProperty("用户资料信息")
    private UserInfoVO userInfo;
}