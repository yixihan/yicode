package com.yixihan.yicode.user.openapi.api.vo.response.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 用户个人资料-vo
 *
 * @author yixihan
 * @date 2022-10-22-17:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户个人资料-vo")
public class UserInfoVO {

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

    @ApiModelProperty(value = "用户技能标签")
    private List<String> userLabel;
    
    @ApiModelProperty(value = "用户个人网址")
    private List<String> userWebsiteList;
    
    @ApiModelProperty(value = "用户语言")
    private List<String> userLanguageList;
}
