package com.yixihan.yicode.user.biz.service.extra.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.user.biz.service.extra.UserFavoriteService;
import com.yixihan.yicode.user.dal.mapper.extra.UserFavoriteMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserFavorite;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收藏夹表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Service
public class UserFavoriteServiceImpl extends ServiceImpl<UserFavoriteMapper, UserFavorite> implements UserFavoriteService {

}
