package com.yixihan.yicode.user.openapi.api.vo.response.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 关注-vo
 *
 * @author yixihan
 * @date 2022/12/21 13:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("关注-vo")
public class FollowVO {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "用户名")
    private String userName;
    
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;
    
    @ApiModelProperty(value = "关注人用户 id")
    private Long followUserId;
    
    @ApiModelProperty(value = "关注人用户名")
    private String followUserName;
    
    @ApiModelProperty(value = "关注人用户头像")
    private String followUserAvatar;
    
    @ApiModelProperty(value = "关注时间")
    private Date createTime;
}
