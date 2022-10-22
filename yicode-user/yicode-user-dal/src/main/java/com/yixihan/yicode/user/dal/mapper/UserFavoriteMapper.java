package com.yixihan.yicode.user.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.user.dal.pojo.UserFavorite;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户收藏表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Mapper
public interface UserFavoriteMapper extends BaseMapper<UserFavorite> {

}
