package com.yixihan.yicode.question.api.dto.request.label;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索标签-dtoReq
 *
 * @author yixihan
 * @date 2023/3/24 14:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索标签-dtoReq")
public class QueryLabelDtoReq extends PageDtoReq {
    
    private String labelName;
}
