package com.yixihan.yicode.question.api.dto.request.question;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 获取用户提交记录-dtoReq
 *
 * @author yixihan
 * @date 2023/1/12 10:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("获取用户提交记录-dtoReq")
public class UserQuestionAnswerDtoReq extends PageReq {
    
    @ApiModelProperty(value = "用户 ID")
    private Long userId;
    
    @ApiModelProperty(value = "是否通过")
    private Boolean accepted;
    
    @ApiModelProperty(value = "问题名称")
    private String questionName;
    
    @ApiModelProperty(value = "问题难度")
    private String questionDifficulty;
    
    @ApiModelProperty(value = "排序:提交时间")
    private Boolean commitDateSort;
    
    @ApiModelProperty(value = "排序:问题名称")
    private Boolean questionNameSort;
}
