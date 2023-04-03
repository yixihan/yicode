package com.yixihan.yicode.user.dal.mapper.extra;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixihan.yicode.user.api.dto.request.extra.FollowQueryDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FollowDtoResult;
import com.yixihan.yicode.user.dal.pojo.extra.UserFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    
    Page<FollowDtoResult> getFollowList(@Param ("params") FollowQueryDtoReq params,
                                        @Param ("page")Page<FollowDtoResult> page);
    
    Page<FollowDtoResult> getFanList(@Param ("params") FollowQueryDtoReq params,
                                     @Param ("page")Page<FollowDtoResult> page);
}
