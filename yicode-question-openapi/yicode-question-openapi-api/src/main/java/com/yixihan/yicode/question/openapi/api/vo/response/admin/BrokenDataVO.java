package com.yixihan.yicode.question.openapi.api.vo.response.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网址活跃数据-vo
 *
 * @author yixihan
 * @date 2023/3/3 21:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("网址活跃数据-vo")
public class BrokenDataVO {
    
    @ApiModelProperty(value = "月份[yyyy-MM]")
    private String month;
    
    @ApiModelProperty(value = "代码提交数")
    private Integer commitCount;
    
    @ApiModelProperty(value = "提交通过数")
    private Integer commitSuccessCount;
    
    @ApiModelProperty(value = "新增题解数")
    private Integer noteCount;
    
    @ApiModelProperty(value = "新增评论数")
    private Integer commentCount;
    
    @ApiModelProperty(value = "新增用户数")
    private Integer userCount;
}
