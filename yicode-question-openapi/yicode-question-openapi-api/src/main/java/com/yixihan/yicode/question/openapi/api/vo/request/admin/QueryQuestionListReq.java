package com.yixihan.yicode.question.openapi.api.vo.request.admin;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索题单-req
 *
 * @author yixihan
 * @date 2023/3/22 9:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索题单-req")
public class QueryQuestionListReq extends PageReq {
    
    @ApiModelProperty(value = "题单")
    private Long favoriteId;
}
