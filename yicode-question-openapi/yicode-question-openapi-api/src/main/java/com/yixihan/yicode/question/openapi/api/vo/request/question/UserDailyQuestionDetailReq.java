package com.yixihan.yicode.question.openapi.api.vo.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询用户每月每日一题情况-req
 *
 * @author yixihan
 * @date 2023/1/12 15:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询用户每日一题情况-req")
public class UserDailyQuestionDetailReq {
    
    @ApiModelProperty(value = "起始月份 [yyyy-MM]")
    private String startMonth;
    
    @ApiModelProperty(value = "终止月份 [yyyy-MM]")
    private String endMonth;
}
