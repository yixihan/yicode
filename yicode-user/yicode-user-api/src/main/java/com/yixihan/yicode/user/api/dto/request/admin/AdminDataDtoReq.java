package com.yixihan.yicode.user.api.dto.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网址数据-dtoReq
 *
 * @author yixihan
 * @date 2023/3/6 9:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("网址数据-dtoReq")
public class AdminDataDtoReq {
    
    @ApiModelProperty(value = "统计维度 : [WEEK : 周, MONTH : 月, YEAR : 年]")
    private String dateDimension;
    
    @ApiModelProperty(value = "起始时间")
    private String startDate;
    
    @ApiModelProperty(value = "终止时间")
    private String endDate;
}
