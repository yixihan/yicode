package com.yixihan.yicode.question.api.dto.request.question;

import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 搜索问题-dtoReq
 *
 * @author yixihan
 * @date 2023/1/12 9:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("搜索问题-dtoReq")
public class QueryQuestionDtoReq extends PageDtoReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "问题名")
    private String questionName;
    
    @ApiModelProperty(value = "难度")
    private String difficulty;
    
    @ApiModelProperty(value = "状态")
    private String status;
    
    @ApiModelProperty(value = "标签")
    private List<Long> label;
    
    @ApiModelProperty(value = "按名称排序")
    private Boolean nameSort;
    
    @ApiModelProperty(value = "按题解排序")
    private Boolean noteSort;
    
    @ApiModelProperty(value = "按难度排序")
    private Boolean difficultySort;
    
    @ApiModelProperty(value = "按通过率排序")
    private Boolean passSort;
    
    @ApiModelProperty(value = "是否查看未启用题目")
    private Boolean enable;
}
