package com.yixihan.yicode.user.dal.pojo.extra;

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
* 用户收藏夹表
*
* @author yixihan
* @since 2022-11-25
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserFavorite对象", description = "用户收藏夹表")
public class UserFavorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "收藏夹 id")
    @TableField(fill = FieldFill.INSERT)
    private Long favoriteId;

    @ApiModelProperty(value = "用户 id")
    private Long userId;

    @ApiModelProperty(value = "收藏类型(QUESTION : 问题, NOTE : 题解)")
    private String favoriteType;

    @ApiModelProperty(value = "收藏夹名")
    private String favoriteName;

    @ApiModelProperty(value = "收藏数量")
    private Integer favoriteCount;

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

    public static final String FAVORITE_ID = "favorite_id";
    
    public static final String USER_ID = "user_id";

    public static final String FAVORITE_TYPE = "favorite_type";

    public static final String FAVORITE_NAME = "favorite_name";

    public static final String FAVORITE_COUNT = "favorite_count";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String VERSION = "version";

    public static final String DEL_FLAG = "del_flag";

}
