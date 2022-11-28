package com.yixihan.yicode.user.biz.service.extra.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.user.api.dto.request.extra.AddFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FavoriteQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
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

    @Override
    public CommonDtoResult<Boolean> addFavorite(AddFavoriteDtoReq dtoReq) {
        return null;
    }

    @Override
    public CommonDtoResult<Boolean> modifyFavorite(ModifyFavoriteDtoReq dtoReq) {
        return null;
    }

    @Override
    public CommonDtoResult<Boolean> delFavorite(Long favoriteId) {
        return null;
    }

    @Override
    public CommonDtoResult<Integer> getFavoriteCount(Long userId) {
        return null;
    }

    @Override
    public PageDtoResult<FavoriteDtoResult> getFavorites(FavoriteQueryDtoReq dtoReq) {
        return null;
    }
}
