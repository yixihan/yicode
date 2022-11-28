package com.yixihan.yicode.user.api.dto.response.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户个人网站-dtoResult
 *
 * @author yixihan
 * @date 2022/11/28 17:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户个人网站-dtoResult")
public class UserWebsiteDtoResult {

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户个人网站")
    private String userWebsite;
}
