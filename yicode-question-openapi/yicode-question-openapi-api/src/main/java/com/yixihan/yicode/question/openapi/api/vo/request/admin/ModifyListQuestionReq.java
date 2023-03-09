package com.yixihan.yicode.question.openapi.api.vo.request.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改题单题目内容-req
 *
 * @author yixihan
 * @date 2023/3/9 9:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改题单题目内容-req")
public class ModifyListQuestionReq {
    
    @ApiModelProperty(value = "题单 id")
    private Long id;
    
    @ApiModelProperty(value = "题目 id")
    private Long questionId;
}
