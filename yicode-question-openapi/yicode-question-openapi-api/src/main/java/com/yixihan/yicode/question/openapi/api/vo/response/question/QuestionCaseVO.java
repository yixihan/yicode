package com.yixihan.yicode.question.openapi.api.vo.response.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 测试用例-vo
 *
 * @author yixihan
 * @date 2023/1/12 10:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("测试用例-vo")
public class QuestionCaseVO {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "是否启用")
    private Boolean enable;
    
    @ApiModelProperty(value = "测试用例参数")
    private String caseParams;
    
    @ApiModelProperty(value = "测试用例答案")
    private String caseAnswer;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
