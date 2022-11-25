package com.yixihan.yicode.user.biz.service.extra.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.user.biz.service.extra.UserFollowService;
import com.yixihan.yicode.user.dal.mapper.extra.UserFollowMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserFollow;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户关注表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollow> implements UserFollowService {

}
