package com.yixihan.yicode.runcode.dal.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 问题答案表
 * </p>
 *
 * @author yixihan
 * @since 2022-10-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="QuestionAnswer对象", description="问题答案表")
public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "问题 id")
    private Long questionId;

    @ApiModelProperty(value = "代码语言类型")
    private String answerType;

    @ApiModelProperty(value = "代码")
    private String answerCode;

    @ApiModelProperty(value = "代码运行结果(acm模式运行结果)")
    private String answerFlag;

    @ApiModelProperty(value = "时间消耗")
    private Integer answerTimeConsume;

    @ApiModelProperty(value = "内存消耗")
    private Integer answerMemoryConsume;

    @ApiModelProperty(value = "备注")
    private String answerNote;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "乐观锁")
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    private Integer isDeleted;


}
