package com.yixihan.yicode.question.dal.pojo.question;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
*
* 问题答案表
*
* @author yixihan
* @since 2023-01-11
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "QuestionAnswer对象", description = "问题答案表")
public class QuestionAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键 id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "问题 id")
    private Long questionId;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer delFlag;


    public static final String ID = "id";

    public static final String QUESTION_ID = "question_id";

    public static final String USER_ID = "user_id";

    public static final String ANSWER_TYPE = "answer_type";

    public static final String ANSWER_CODE = "answer_code";

    public static final String ANSWER_FLAG = "answer_flag";

    public static final String ANSWER_TIME_CONSUME = "answer_time_consume";

    public static final String ANSWER_MEMORY_CONSUME = "answer_memory_consume";

    public static final String ANSWER_NOTE = "answer_note";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String VERSION = "version";

    public static final String DEL_FLAG = "del_flag";

}
