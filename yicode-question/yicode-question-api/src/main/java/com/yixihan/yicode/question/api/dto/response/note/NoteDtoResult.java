package com.yixihan.yicode.question.api.dto.response.note;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 题解-dtoResult
 *
 * @author yixihan
 * @date 2023/1/11 17:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("题解-dtoResult")
public class NoteDtoResult {
    
    @ApiModelProperty(value = "问题 id")
    private Long questionId;
    
    @ApiModelProperty(value = "题解 id")
    private Long noteId;
    
    @ApiModelProperty(value = "题解标题")
    private String noteName;
    
    @ApiModelProperty(value = "题解内容")
    private String noteContent;
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "点赞数量")
    private Integer likeCount;
    
    @ApiModelProperty(value = "评论数量")
    private Integer commentCount;
    
    @ApiModelProperty(value = "收藏数量")
    private Integer collectionCount;
    
    @ApiModelProperty(value = "阅读数量")
    private Integer readCount;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
    @ApiModelProperty(value = "乐观锁")
    private Integer version;
    
    @ApiModelProperty(value = "逻辑删除")
    private Integer delFlag;
}
