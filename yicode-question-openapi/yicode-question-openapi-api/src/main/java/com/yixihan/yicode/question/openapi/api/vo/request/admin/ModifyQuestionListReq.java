package com.yixihan.yicode.question.openapi.api.vo.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改题单-req
 *
 * @author yixihan
 * @date 2023/3/9 9:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改题单-req")
public class ModifyQuestionListReq {
    
    @ApiModelProperty(value = "题单 id")
    private Long id;
    
    @ApiModelProperty(value = "题单名")
    private String questionListName;
    
    @ApiModelProperty(value = "题单背景")
    private String questionListBg;
}
