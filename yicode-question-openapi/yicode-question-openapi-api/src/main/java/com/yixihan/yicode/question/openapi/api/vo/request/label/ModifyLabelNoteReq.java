package com.yixihan.yicode.question.openapi.api.vo.request.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加题解标签-req
 *
 * @author yixihan
 * @date 2023/1/15 23:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加题解标签-req")
public class ModifyLabelNoteReq {
    
    @ApiModelProperty(value = "标签 ID")
    private Long labelId;
    
    @ApiModelProperty(value = "题解 ID")
    private Long noteId;
}
