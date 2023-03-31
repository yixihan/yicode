package com.yixihan.yicode.question.openapi.api.vo.request.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 修改题解标签-req
 *
 * @author yixihan
 * @date 2023/1/15 23:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改题解标签-req")
public class ModifyLabelNoteReq {
    
    @ApiModelProperty(value = "题解 ID")
    private Long noteId;
    
    @ApiModelProperty(value = "已有标签 ID")
    private List<Long> labelIdList;
    
    @ApiModelProperty(value = "新建标签名字")
    private List<String> labelNameList;
}
