package com.yixihan.yicode.user.api.dto.response.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户语言-dtoResult
 *
 * @author yixihan
 * @date 2022/11/28 17:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户语言-dtoResult")
public class UserLanguageDtoResult {

    @ApiModelProperty(value = "语言名")
    private String language;

    @ApiModelProperty(value = "解决问题数量")
    private Integer dealCount;
}
