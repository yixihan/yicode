package com.yixihan.yicode.user.api.dto.response.extra;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 关注-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 16:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("关注-dtoReq")
public class FollowDtoResult {

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
    
    @ApiModelProperty(value = "是否互关")
    private Boolean flag;

    @ApiModelProperty(value = "关注时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
    @ApiModelProperty(value = "乐观锁")
    private Integer version;
    
    @ApiModelProperty(value = "逻辑删除")
    private Integer delFlag;
}
