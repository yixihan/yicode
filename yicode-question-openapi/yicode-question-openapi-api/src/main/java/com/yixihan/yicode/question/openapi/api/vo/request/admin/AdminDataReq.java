package com.yixihan.yicode.question.openapi.api.vo.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网址数据-req
 *
 * @author yixihan
 * @date 2023/3/3 21:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("网址数据-req")
public class AdminDataReq {
    
    @ApiModelProperty(value = "统计维度 : [WEEK : 周, MONTH : 月, YEAR : 年]")
    private String dateDimension;
}
