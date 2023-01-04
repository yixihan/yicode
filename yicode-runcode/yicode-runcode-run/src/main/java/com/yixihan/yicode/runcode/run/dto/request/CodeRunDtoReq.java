package com.yixihan.yicode.runcode.run.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * description
 *
 * @author yixihan
 * @date 2023/1/3 15:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("代码运行-req")
public class CodeRunDtoReq {
    
    @ApiModelProperty(value = "要执行的代码")
    private String code;
    
    @ApiModelProperty(value = "代码类型")
    private String codeType;
    
    @ApiModelProperty(value = "形参")
    private List<List<String>> paramList;
}
