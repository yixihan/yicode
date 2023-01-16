package com.yixihan.yicode.question.openapi.api.vo.request.question;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索用户题目通过记录-dtoReq
 *
 * @author yixihan
 * @date 2023/1/12 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索用户题目通过记录-dtoReq")
public class QueryUserRecordReq extends PageDtoReq {
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
}
