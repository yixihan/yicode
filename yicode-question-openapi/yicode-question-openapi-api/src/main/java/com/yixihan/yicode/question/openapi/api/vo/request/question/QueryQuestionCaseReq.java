package com.yixihan.yicode.question.openapi.api.vo.request.question;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索问题测试用例-req
 *
 * @author yixihan
 * @date 2023/3/28 9:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索问题测试用例-req")
public class QueryQuestionCaseReq extends PageReq {
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
}
