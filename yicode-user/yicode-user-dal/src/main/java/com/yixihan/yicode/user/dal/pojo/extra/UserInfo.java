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
 * 用户信息表
 *
 * @author yixihan
 * @since 2022-11-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserInfo对象", description = "用户信息表")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "主键 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @ApiModelProperty(value = "用户 id")
    private Long userId;
    
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;
    
    @ApiModelProperty(value = "用户省份")
    private String userProvince;
    
    @ApiModelProperty(value = "用户城市")
    private String userCity;
    
    @ApiModelProperty(value = "用户性别[SECRECY:保密, MAN:男, WOMAN:女]")
    private String userSex;
    
    @ApiModelProperty(value = "用户签名")
    private String userSign;
    
    @ApiModelProperty(value = "用户学校")
    private String userSchool;
    
    @ApiModelProperty(value = "用户生日")
    private Date userBirth;
    
    @ApiModelProperty(value = "用户真实姓名")
    private String userRealName;
    
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
    
    public static final String USER_ID = "user_id";
    
    public static final String USER_AVATAR = "user_avatar";
    
    public static final String USER_PROVINCE = "user_province";
    
    public static final String USER_CITY = "user_city";
    
    public static final String USER_SEX = "user_sex";
    
    public static final String USER_SIGN = "user_sign";
    
    public static final String USER_SCHOOL = "user_school";
    
    public static final String USER_BIRTH = "user_birth";
    
    public static final String USER_REAL_NAME = "user_real_name";
    
    public static final String CREATE_TIME = "create_time";
    
    public static final String UPDATE_TIME = "update_time";
    
    public static final String VERSION = "version";
    
    public static final String DEL_FLAG = "del_flag";

}
