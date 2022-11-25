package com.yixihan.yicode.user.dal.mapper.extra;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserFollow;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户关注表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Mapper
public interface UserFollowMapper extends BaseMapper<UserFollow> {

}
