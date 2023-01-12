package com.yixihan.yicode.question.api.dto.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询用户每月每日一题情况-dtoReq
 *
 * @author yixihan
 * @date 2023/1/12 15:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询用户每月每日一题情况-dtoReq")
public class MonthUserDailyQuestionDetailDtoReq {
    
    @ApiModelProperty(value = "月份 [yyyy-MM]")
    private String month;
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
}
