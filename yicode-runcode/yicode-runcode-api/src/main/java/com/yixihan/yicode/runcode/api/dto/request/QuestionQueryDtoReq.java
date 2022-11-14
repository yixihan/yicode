package com.yixihan.yicode.runcode.api.dto.request;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 问题查询-dtoReq
 *
 * @author yixihan
 * @date 2022/11/14 10:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("问题查询-dtoReq")
public class QuestionQueryDtoReq extends PageDtoReq {

    private String type;
}
