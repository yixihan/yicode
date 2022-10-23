package com.yixihan.yicode.auth.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Role对象", description="角色表")
public class Role implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键 id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色 id")
    private Long roleId;

    @ApiModelProperty(value = "角色名")
    private String roleName;


    /**
     * 获取用户的权限信息
     *
     * @return
     */
    @Override
    public String getAuthority() {
        return roleName;
    }
}
