package com.yixihan.yicode.question.openapi.api.vo.response.question;

import com.yixihan.yicode.common.enums.question.CodeAnswerEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 代码运行-vo
 *
 * @author yixihan
 * @date 2023/3/21 11:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("代码运行-vo")
public class CodeRunVO {
    
    @ApiModelProperty(value = "代码测评结果")
    CodeAnswerEnums status;
    
    @ApiModelProperty(value = "代码运行结果")
    private List<String> ansList;
    
    @ApiModelProperty(value = "时间消耗")
    private Long useTime;
    
    @ApiModelProperty(value = "内存消耗")
    private Double useMemory;
    
    @ApiModelProperty(value = "是否编译成功")
    private Boolean compile;
    
    @ApiModelProperty(value = "是否运行成功")
    private Boolean run;
}
