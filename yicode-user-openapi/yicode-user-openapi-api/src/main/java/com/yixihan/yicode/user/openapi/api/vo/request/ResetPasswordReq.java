package com.yixihan.yicode.user.openapi.api.vo.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 重置密码-req
 *
 * @author yixihan
 * @date 2022/11/9 11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("重置密码-req")
public class ResetPasswordReq {

    private String mobile;
}
