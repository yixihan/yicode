package com.yixihan.yicode.question.openapi.api.vo.response.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 问题明细-dtoResult
 *
 * @author yixihan
 * @date 2023/1/12 9:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("问题明细-dtoResult")
public class QuestionDetailVO extends QuestionVO {
    
    @ApiModelProperty(value = "问题描述")
    private String questionDesc;
}
