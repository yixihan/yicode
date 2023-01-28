package com.yixihan.yicode.question.api.dto.response.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户提交情况-dtoResult
 *
 * @author yixihan
 * @date 2023/1/28 14:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户提交情况-dtoResult")
public class CommitRecordDtoResult {
    
    @ApiModelProperty(value = "日期")
    private String date;
    
    @ApiModelProperty(value = "提交次数")
    private Integer count;
}
