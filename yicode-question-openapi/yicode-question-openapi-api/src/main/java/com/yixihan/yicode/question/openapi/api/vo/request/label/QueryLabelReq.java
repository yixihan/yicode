package com.yixihan.yicode.question.openapi.api.vo.request.label;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索标签-req
 *
 * @author yixihan
 * @date 2023/3/24 14:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索标签-req")
public class QueryLabelReq extends PageReq {
    
    private String labelName;
}
