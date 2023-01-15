package com.yixihan.yicode.question.openapi.api.vo.request.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加标签-req
 *
 * @author yixihan
 * @date 2023/1/15 23:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加标签-req")
public class AddLabelReq {
    
    @ApiModelProperty(value = "标签名")
    private String labelName;
}
