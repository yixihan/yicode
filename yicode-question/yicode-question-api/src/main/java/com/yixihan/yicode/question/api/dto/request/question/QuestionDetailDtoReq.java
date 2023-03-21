package com.yixihan.yicode.question.api.dto.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目详情查看-dtoReq
 *
 * @author yixihan
 * @date 2023/3/21 14:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("题目详情查看-dtoReq")
public class QuestionDetailDtoReq {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "题目 id")
    private Long questionId;
}
