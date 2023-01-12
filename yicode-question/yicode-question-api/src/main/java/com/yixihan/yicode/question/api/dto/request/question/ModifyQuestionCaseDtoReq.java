package com.yixihan.yicode.question.api.dto.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改测试用例-dtoReq
 *
 * @author yixihan
 * @date 2023/1/12 10:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改测试用例-dtoReq")
public class ModifyQuestionCaseDtoReq {
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "是否启用")
    private Integer enable;
    
    @ApiModelProperty(value = "测试用例参数")
    private String caseParams;
    
    @ApiModelProperty(value = "测试用例答案")
    private String caseAnswer;
}
