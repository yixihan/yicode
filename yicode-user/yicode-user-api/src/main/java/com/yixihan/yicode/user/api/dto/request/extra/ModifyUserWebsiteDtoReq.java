package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 更新用户个人网站-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 17:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("更新用户个人网站-dtoReq")
public class ModifyUserWebsiteDtoReq {

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "用户个人网站")
    private List<String> userWebsite;
}
