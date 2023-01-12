package com.yixihan.yicode.question.api.dto.response.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试用例-dtoResult
 *
 * @author yixihan
 * @date 2023/1/12 10:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("测试用例-dtoResult")
public class QuestionCaseDtoResult {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "是否启用")
    private Integer enable;
    
    @ApiModelProperty(value = "测试用例参数")
    private String caseParams;
    
    @ApiModelProperty(value = "测试用例答案")
    private String caseAnswer;
}
