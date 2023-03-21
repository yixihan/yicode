package com.yixihan.yicode.question.openapi.api.vo.response.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 每日一题-vo
 *
 * @author yixihan
 * @date 2023/1/17 11:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("每日一题-vo")
public class QuestionDailyVO {
    
    @ApiModelProperty(value = "主键 id")
    private Long id;
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
