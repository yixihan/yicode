package com.yixihan.yicode.runcode.openapi.api.vo.request;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 问题查询-req
 *
 * @author yixihan
 * @date 2022/11/14 10:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("问题查询-req")
public class QuestionQueryReq extends PageReq {

    private String type;
}
