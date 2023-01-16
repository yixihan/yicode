package com.yixihan.yicode.question.api.dto.response.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户题目通过记录-dtoResult
 *
 * @author yixihan
 * @date 2023/1/12 10:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户题目通过记录-dtoResult")
public class UserRecordDtoResult {
    
    @ApiModelProperty(value = "主键 ID")
    private Long id;
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "题目 id")
    private Long questionId;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
}
