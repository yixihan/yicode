package com.yixihan.yicode.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel(value="User对象", description="用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "盐")
    private String userSalt;

    @ApiModelProperty(value = "用户手机号")
    private String userMobile;

    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;

    @ApiModelProperty(value = "用户角色列表")
    @TableField(exist = false)
    private List<Role> authorities;

    @ApiModelProperty(value = "逻辑删除")
    private Integer isDeleted;
}
