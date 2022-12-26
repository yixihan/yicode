package com.yixihan.yicode.user.dal.mapper.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yixihan.yicode.user.api.dto.response.base.UserCommonDtoResult;
import com.yixihan.yicode.user.dal.pojo.base.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2022-10-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
    /**
     * 获取用户常用信息
     *
     * @param userIdList 用户 ID 列表
     * @return List {@link UserCommonDtoResult}
     */
    List<UserCommonDtoResult> getUserCommonInfo(@Param ("userIdList") List<Long> userIdList);
    
}
