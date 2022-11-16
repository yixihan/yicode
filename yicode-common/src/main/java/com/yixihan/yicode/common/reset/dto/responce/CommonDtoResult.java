package com.yixihan.yicode.common.reset.dto.responce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用-dtoResult
 *
 * @author yixihan
 * @date 2022/11/16 13:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("通用-dtoResult")
public class CommonDtoResult<T> {

    @ApiModelProperty(value = "数据")
    private T data;

    @ApiModelProperty(value = "信息")
    private String message;
}
