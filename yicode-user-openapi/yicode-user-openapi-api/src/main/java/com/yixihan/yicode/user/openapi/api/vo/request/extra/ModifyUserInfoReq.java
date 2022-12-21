package com.yixihan.yicode.user.openapi.api.vo.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 更新用户资料-req
 *
 * @author yixihan
 * @date 2022/12/21 13:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("更新用户资料-req")
public class ModifyUserInfoReq {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;
    
    @ApiModelProperty(value = "用户省份")
    private String userProvince;
    
    @ApiModelProperty(value = "用户城市")
    private String userCity;
    
    @ApiModelProperty(value = "用户性别(0:保密, 1:男, 2:女)")
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
    
    @ApiModelProperty(value = "用户个人网址")
    private List<String> userWebsiteList;
    
    @ApiModelProperty(value = "用户语言")
    private List<String> userLanguageList;
}