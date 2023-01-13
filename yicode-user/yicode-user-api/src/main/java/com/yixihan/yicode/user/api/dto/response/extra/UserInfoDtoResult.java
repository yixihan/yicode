package com.yixihan.yicode.user.api.dto.response.extra;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户信息-dtoResult
 *
 * @author yixihan
 * @date 2022-10-22-18:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户信息-dtoResult")
public class UserInfoDtoResult {

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户头像")
    private String userAvatar;

    @ApiModelProperty(value = "用户省份")
    private String userProvince;

    @ApiModelProperty(value = "用户城市")
    private String userCity;

    @ApiModelProperty(value = "用户性别(SECRECY:保密, MAN:男, WOMAN:女)")
    private String userSex;

    @ApiModelProperty(value = "用户签名")
    private String userSign;

    @ApiModelProperty(value = "用户学校")
    private String userSchool;

    @ApiModelProperty(value = "用户生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date userBirth;

    @ApiModelProperty(value = "用户真实姓名")
    private String userRealName;
    
}
