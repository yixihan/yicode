package com.yixihan.yicode.user.api.dto.request.extra;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新用户语言-dtoReq
 *
 * @author yixihan
 * @date 2022/11/28 17:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("更新用户语言-dtoReq")
public class ModifyUserLanguageDtoReq {

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "语言名")
    private String language;

    @ApiModelProperty(value = "解决问题数量")
    private Integer dealCount;
}
