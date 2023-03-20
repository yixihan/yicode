package com.yixihan.yicode.question.openapi.api.vo.response.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 查询用户每月每日一题情况-vo
 *
 * @author yixihan
 * @date 2023/1/12 15:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询用户每月每日一题情况-vo")
public class UserDailyQuestionVO {
    
    @ApiModelProperty(value = "主键 id")
    private Long id;
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
