package com.yixihan.yicode.user.dal.mapper.extra;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserFavorite;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户收藏表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Mapper
public interface UserFavoriteMapper extends BaseMapper<UserFavorite> {

}
