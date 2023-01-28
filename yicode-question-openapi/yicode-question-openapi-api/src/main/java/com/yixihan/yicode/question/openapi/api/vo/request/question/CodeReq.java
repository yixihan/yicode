package com.yixihan.yicode.question.openapi.api.vo.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代码提交-req
 *
 * @author yixihan
 * @date 2023/1/28 9:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("代码提交-req")
public class CodeReq {
    
    @ApiModelProperty(value = "代码")
    private String code;
    
    @ApiModelProperty(value = "代码类型")
    private String type;
}
