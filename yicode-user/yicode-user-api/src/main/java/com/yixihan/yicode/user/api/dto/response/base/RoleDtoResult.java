package com.yixihan.yicode.user.api.dto.response.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 角色-dtoResult
 *
 * @author yixihan
 * @date 2022-10-22-18:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("角色-dtoResult")
public class RoleDtoResult {
    
    @ApiModelProperty(value = "角色 id")
    private Long roleId;
    
    @ApiModelProperty(value = "角色名")
    private String roleName;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
    @ApiModelProperty(value = "乐观锁")
    private Integer version;
    
    @ApiModelProperty(value = "逻辑删除")
    private Integer delFlag;
}
