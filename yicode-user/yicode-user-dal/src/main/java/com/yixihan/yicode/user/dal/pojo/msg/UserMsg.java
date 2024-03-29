package com.yixihan.yicode.user.dal.pojo.msg;

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
* 用户消息表
*
* @author yixihan
* @since 2022-11-25
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserMsg对象", description = "用户消息表")
public class UserMsg implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "发送者 id")
    private Long sendUserId;
    
    @ApiModelProperty(value = "发送者用户名")
    private String sendUserName;
    
    @ApiModelProperty(value = "接收者 id")
    private Long receiveUseId;
    
    @ApiModelProperty(value = "消息内容")
    private String msg;
    
    @ApiModelProperty(value = "是否已读")
    private Boolean finish;
    
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
    
    public static final String SEND_USER_ID = "send_user_id";
    
    public static final String SEND_USER_NAME = "send_user_name";
    
    public static final String RECEIVE_USE_ID = "receive_use_id";
    
    public static final String MSG = "msg";
    
    public static final String FINISH = "finish";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";

}
