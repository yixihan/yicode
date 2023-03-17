package com.yixihan.yicode.user.api.dto.response.extra;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户标签-dtoResult
 *
 * @author yixihan
 * @date 2022/12/21 16:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户标签-dtoResult")
public class UserLabelDtoResult {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "标签 id")
    private Long labelId;
    
    @ApiModelProperty(value = "标签名")
    private String labelName;
    
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
