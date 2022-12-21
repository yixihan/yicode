package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 更新用户资料-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 16:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("更新用户资料-dtoReq")
public class ModifyUserInfoDtoReq {

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户头像")
    private String userAvatar;

    @ApiModelProperty(value = "用户省份")
    private String userProvince;

    @ApiModelProperty(value = "用户城市")
    private String userCity;

    @ApiModelProperty(value = "用户性别(SECRECY:保密, MAN:男, WOMAN:女)")
    private Integer userSex;

    @ApiModelProperty(value = "用户签名")
    private String userSign;

    @ApiModelProperty(value = "用户学校")
    private String userSchool;

    @ApiModelProperty(value = "用户生日")
    private Date userBirth;

    @ApiModelProperty(value = "用户真实姓名")
    private String userRealName;

    @ApiModelProperty(value = "用户技能标签")
    private String userLabel;
}
