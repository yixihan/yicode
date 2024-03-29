package com.yixihan.yicode.question.api.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网址代码提交数据-dtoResult
 *
 * @author yixihan
 * @date 2023/3/3 21:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("网址代码提交数据-dtoResult")
public class CommitDataDtoResult {
    
    @ApiModelProperty(value = "代码提交数")
    private Integer commitCount;
    
    @ApiModelProperty(value = "提交通过数")
    private Integer commitSuccessCount;
    
    @ApiModelProperty(value = "简单题代码提交数")
    private Integer commitEasyCount;
    
    @ApiModelProperty(value = "简单题提交通过数")
    private Integer commitSuccessEasyCount;
    
    @ApiModelProperty(value = "中等题代码提交数")
    private Integer commitMediumCount;
    
    @ApiModelProperty(value = "中等题提交通过数")
    private Integer commitSuccessMediumCount;
    
    @ApiModelProperty(value = "困难题代码提交数")
    private Integer commitHardCount;
    
    @ApiModelProperty(value = "困难题提交通过数")
    private Integer commitSuccessHardCount;
    
}
