package com.yixihan.yicode.user.api.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户详细信息-dtoResult
 *
 * @author yixihan
 * @date 2022-10-22-17:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户详细信息-dtoResult")
public class UserDetailInfoDtoResult {

    @ApiModelProperty("用户信息")
    private UserDtoResult user;

    @ApiModelProperty("用户权限信息")
    private List<RoleDtoResult> userRoleList;

    @ApiModelProperty("用户资料信息")
    private UserInfoDtoResult userInfo;
}
