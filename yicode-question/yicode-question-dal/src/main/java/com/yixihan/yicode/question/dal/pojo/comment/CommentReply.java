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
* 子评论表
*
* @author yixihan
* @since 2023-01-11
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CommentReply对象", description = "子评论表")
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "子评论 id")
    @TableId(value = "reply_id", type = IdType.AUTO)
    private Long replyId;
    
    @ApiModelProperty(value = "父评论 id")
    private Long rootId;
    
    @ApiModelProperty(value = "评论者 id")
    private Long userId;
    
    @ApiModelProperty(value = "回复用户 id")
    private Long replyUserId;
    
    @ApiModelProperty(value = "评论内容")
    private String content;
    
    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;
    
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
    
    public static final String REPLY_ID = "reply_id";
    
    public static final String ROOT_ID = "root_id";
    
    public static final String USER_ID = "user_id";
    
    public static final String REPLY_USER_ID = "reply_user_id";
    
    public static final String CONTENT = "content";
    
    public static final String LIKE_COUNT = "like_count";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";

}
