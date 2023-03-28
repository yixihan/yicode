package com.yixihan.yicode.user.openapi.api.vo.request.base;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索用户-req
 *
 * @author yixihan
 * @date 2023/3/27 21:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索用户-req")
public class QueryUserReq extends PageReq {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "用户 用户名")
    private String userName;
    
    @ApiModelProperty(value = "用户 手机号")
    private String userMobile;
    
    @ApiModelProperty(value = "用户 邮箱")
    private String userEmail;
}
