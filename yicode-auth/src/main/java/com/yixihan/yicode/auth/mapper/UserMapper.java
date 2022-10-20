package com.yixihan.yicode.auth.mapper;

import com.yixihan.yicode.auth.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2022-10-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
