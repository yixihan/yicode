package com.yixihan.yicode.question.dal.pojo.comment;

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
* 父评论表
*
* @author yixihan
* @since 2023-01-11
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CommentRoot对象", description = "父评论表")
public class CommentRoot implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "父评论 id")
    @TableId(value = "root_id", type = IdType.ASSIGN_ID)
    private Long rootId;

    @ApiModelProperty(value = "评论内容 id")
    private Long answerId;

    @ApiModelProperty(value = "评论内容类型(QUESTION:问题, NOTE:题解)")
    private String answerType;

    @ApiModelProperty(value = "评论者 id")
    private Long userId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;

    @ApiModelProperty(value = "回复数")
    private Integer replyCount;

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

    public static final String ROOT_ID = "root_id";

    public static final String ANSWER_ID = "answer_id";

    public static final String ANSWER_TYPE = "answer_type";

    public static final String USER_ID = "user_id";

    public static final String CONTENT = "content";

    public static final String LIKE_COUNT = "like_count";

    public static final String REPLY_COUNT = "reply_count";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String VERSION = "version";

    public static final String DEL_FLAG = "del_flag";

}
