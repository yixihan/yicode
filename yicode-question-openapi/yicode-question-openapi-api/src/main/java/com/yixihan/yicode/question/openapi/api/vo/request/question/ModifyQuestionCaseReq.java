package com.yixihan.yicode.question.openapi.api.vo.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改测试用例-req
 *
 * @author yixihan
 * @date 2023/1/12 10:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改测试用例-req")
public class ModifyQuestionCaseReq {
    
    @ApiModelProperty(value = "测试用例 id")
    private Long id;
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "是否启用(0:不启用, 1:启用)")
    private Integer enable;
    
    @ApiModelProperty(value = "测试用例参数")
    private String caseParams;
    
    @ApiModelProperty(value = "测试用例答案")
    private String caseAnswer;
}
