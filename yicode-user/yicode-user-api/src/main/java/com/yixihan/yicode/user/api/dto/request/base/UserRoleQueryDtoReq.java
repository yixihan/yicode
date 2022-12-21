package com.yixihan.yicode.user.api.dto.request.base;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户角色查询-req
 *
 * @author yixihan
 * @date 2022/12/21 10:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户角色查询-req")
public class UserRoleQueryDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
}
