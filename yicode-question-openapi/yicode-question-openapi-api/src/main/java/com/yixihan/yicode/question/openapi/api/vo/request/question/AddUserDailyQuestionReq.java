package com.yixihan.yicode.question.openapi.api.vo.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加每日一题用户做题情况-req
 *
 * @author yixihan
 * @date 2023/1/12 15:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加每日一题用户做题情况-req")
public class AddUserDailyQuestionReq {
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
}
