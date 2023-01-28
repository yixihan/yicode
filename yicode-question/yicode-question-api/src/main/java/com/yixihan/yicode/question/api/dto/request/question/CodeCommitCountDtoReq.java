package com.yixihan.yicode.question.api.dto.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取用户提交代码次数记录-dtoReq
 *
 * @author yixihan
 * @date 2023/1/28 13:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获取用户提交代码次数记录-dtoReq")
public class CodeCommitCountDtoReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "统计截至日期")
    private String endDate;
}
