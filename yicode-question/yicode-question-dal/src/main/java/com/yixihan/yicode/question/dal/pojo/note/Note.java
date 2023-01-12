package com.yixihan.yicode.question.dal.pojo.note;

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
* 问题题解表
*
* @author yixihan
* @since 2023-01-11
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Note对象", description = "问题题解表")
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "问题 id")
    private Long questionId;

    @ApiModelProperty(value = "题解 id")
    @TableField(fill = FieldFill.INSERT)
    private Long noteId;

    @ApiModelProperty(value = "题解标题")
    private String noteName;

    @ApiModelProperty(value = "题解内容")
    private String noteContent;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "点赞数量")
    private Integer likeCount;

    @ApiModelProperty(value = "收藏数量")
    private Integer collectionCount;

    @ApiModelProperty(value = "阅读数量")
    private Integer readCount;

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

    public static final String NOTE_ID = "note_id";

    public static final String NOTE_NAME = "note_name";

    public static final String NOTE_CONTENT = "note_content";

    public static final String USER_ID = "user_id";

    public static final String LIKE_COUNT = "like_count";

    public static final String COLLECTION_COUNT = "collection_count";

    public static final String READ_COUNT = "read_count";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String VERSION = "version";

    public static final String DEL_FLAG = "del_flag";

}
