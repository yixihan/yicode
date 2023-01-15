package com.yixihan.yicode.question.openapi.api.vo.request.note;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 修改题解-req
 *
 * @author yixihan
 * @date 2023/1/11 15:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改题解-req")
public class ModifyNoteReq {
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "题解 id")
    private Long noteId;
    
    @ApiModelProperty(value = "题解标题")
    private String noteName;
    
    @ApiModelProperty(value = "题解内容")
    private String noteContent;
}
