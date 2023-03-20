package com.yixihan.yicode.question.openapi.api.vo.response.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 收藏内容-vo
 *
 * @author yixihan
 * @date 2022/12/21 10:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("收藏内容-vo")
public class CollectionVO {
    
    @ApiModelProperty(value = "收藏夹 id")
    private Long favoriteId;
    
    @ApiModelProperty(value = "用户收藏内容 id")
    private Long collectionId;
    
    @ApiModelProperty(value = "用户收藏内容 名字")
    private String collectionName;
    
    @ApiModelProperty(value = "收藏时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
