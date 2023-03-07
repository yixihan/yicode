package com.yixihan.yicode.question.api.dto.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网址数据-dtoResult
 *
 * @author yixihan
 * @date 2023/3/6 9:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("网址数据-dtoResult")
public class BrokenDataDtoResult {
    
    @ApiModelProperty(value = "月份[yyyy-MM]")
    private String month;
    
    @ApiModelProperty(value = "代码提交数")
    private Integer commitCount = 0;
    
    @ApiModelProperty(value = "提交通过数")
    private Integer commitSuccessCount = 0;
    
    @ApiModelProperty(value = "新增题解数")
    private Integer noteCount = 0;
    
    @ApiModelProperty(value = "新增评论数")
    private Integer commentCount = 0;
    
    @ApiModelProperty(value = "新增用户数")
    private Integer userCount = 0;
}
