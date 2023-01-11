package com.yixihan.yicode.question.api.dto.request.note;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 搜索题解-dtoReq
 *
 * @author yixihan
 * @date 2023/1/11 17:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("搜索题解-dtoReq")
public class QueryNoteDtoReq {
    
    @ApiModelProperty(value = "题解 ID")
    private String noteId;
    
    @ApiModelProperty(value = "题解标题")
    private String noteName;
    
    @ApiModelProperty(value = "题解标签")
    private List<Long> labelIdList;
    
    @ApiModelProperty(value = "排序:点赞")
    private Boolean likeCount;
    
    @ApiModelProperty(value = "排序:最新发布")
    private Boolean createDesc;
    
    @ApiModelProperty(value = "排序:最早发布")
    private Boolean createAsc;
}
