package com.yixihan.yicode.question.openapi.api.vo.response.label;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 标签-标签-vo
 *
 * @author yixihan
 * @date 2023/1/11 14:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("标签-vo")
public class LabelVO {
    
    @ApiModelProperty(value = "标签 id")
    private Long labelId;
    
    @ApiModelProperty(value = "标签名")
    private String labelName;
    
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
