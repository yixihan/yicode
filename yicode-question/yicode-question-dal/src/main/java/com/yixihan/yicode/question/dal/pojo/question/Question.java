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
* 问题表
*
* @author yixihan
* @since 2023-01-11
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Question对象", description = "问题表")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "问题 id")
    @TableId(value = "question_id", type = IdType.AUTO)
    private Long questionId;

    @ApiModelProperty(value = "问题题目")
    private String questionName;

    @ApiModelProperty(value = "问题描述")
    private String questionDesc;

    @ApiModelProperty(value = "问题难度")
    private String questionDifficulty;

    @ApiModelProperty(value = "问题点赞数")
    private Integer likeCount;
    
    @ApiModelProperty(value = "问题题解数")
    private Integer noteCount;
    
    @ApiModelProperty(value = "问题评论数")
    private Integer commentCount;

    @ApiModelProperty(value = "问题提交数")
    private Integer commitCount;

    @ApiModelProperty(value = "问题通过数")
    private Integer successCount;

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

    public static final String QUESTION_NAME = "question_name";

    public static final String QUESTION_DESC = "question_desc";

    public static final String QUESTION_DIFFICULTY = "question_difficulty";

    public static final String LIKE_COUNT = "like_count";

    public static final String COMMIT_COUNT = "commit_count";

    public static final String SUCCESS_COUNT = "success_count";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String VERSION = "version";

    public static final String DEL_FLAG = "del_flag";

}
