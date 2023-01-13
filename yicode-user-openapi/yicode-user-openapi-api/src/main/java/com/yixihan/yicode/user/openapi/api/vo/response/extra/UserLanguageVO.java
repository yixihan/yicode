package com.yixihan.yicode.user.openapi.api.vo.response.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户语言-vo
 *
 * @author yixihan
 * @date 2022/11/28 17:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户语言-vo")
public class UserLanguageVO {

    @ApiModelProperty(value = "语言名")
    private String language;

    @ApiModelProperty(value = "解决问题数量")
    private Integer dealCount;
}
