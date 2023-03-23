package com.yixihan.yicode.thirdpart.dal.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 邮件模板表
 * </p>
 *
 * @author yixihan
 * @since 2022-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TemplateEmail对象", description="邮件模板表")
public class TemplateEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "模板名字")
    private String templateName;
    
    @ApiModelProperty(value = "模板内容")
    private String templateContent;
    
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
    
    public static final String TEMPLATE_NAME = "template_name";
    
    public static final String TEMPLATE_CONTENT = "template_content";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";
    
}
