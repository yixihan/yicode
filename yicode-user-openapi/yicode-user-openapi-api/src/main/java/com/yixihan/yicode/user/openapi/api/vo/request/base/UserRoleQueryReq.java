package com.yixihan.yicode.user.openapi.api.vo.request.base;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * description
 *
 * @author yixihan
 * @date 2022/12/21 14:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户角色查询-req")
public class UserRoleQueryReq extends PageReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
}
