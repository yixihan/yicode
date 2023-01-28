package com.yixihan.yicode.question.openapi.api.vo.request.question;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 获取用户问题提交记录-req
 *
 * @author yixihan
 * @date 2023/1/12 10:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("获取用户问题提交记录-req")
public class QuestionAnswerReq extends PageReq {
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
}
