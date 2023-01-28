package com.yixihan.yicode.question.openapi.api.vo.request.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加用户题目通过记录-req
 *
 * @author yixihan
 * @date 2023/1/12 10:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加用户题目通过记录-req")
public class AddQuestionUserRecordReq {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "题目 id")
    private Long questionId;
}
