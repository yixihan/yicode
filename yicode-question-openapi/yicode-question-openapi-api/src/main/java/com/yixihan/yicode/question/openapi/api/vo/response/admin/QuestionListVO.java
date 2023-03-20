package com.yixihan.yicode.question.openapi.api.vo.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 题单-vo
 *
 * @author yixihan
 * @date 2023/3/9 9:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("题单-vo")
public class QuestionListVO {
    
    @ApiModelProperty(value = "题单")
    private Long favoriteId;
    
    @ApiModelProperty(value = "收藏夹名")
    private String favoriteType;
    
    @ApiModelProperty(value = "收藏数量")
    private String favoriteName;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
}
