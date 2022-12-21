package com.yixihan.yicode.user.api.dto.response.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
