package com.yixihan.yicode.question.api.dto.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加问题答案备注-dtoReq
 *
 * @author yixihan
 * @date 2023/1/12 10:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加问题答案备注-dtoReq")
public class AddQuestionNoteDtoReq {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "备注")
    private String answerNote;
}
