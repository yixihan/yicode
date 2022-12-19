package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.extra.AddFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FavoriteQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserFavoriteService;
import com.yixihan.yicode.user.dal.mapper.extra.UserFavoriteMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserFavorite;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        UserFavorite favorite = CopyUtils.copySingle (UserFavorite.class, dtoReq);
        int insert = baseMapper.insert (favorite);
        if (insert == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public CommonDtoResult<Boolean> modifyFavorite(ModifyFavoriteDtoReq dtoReq) {
        UpdateWrapper<UserFavorite> wrapper = new UpdateWrapper<> ();
        wrapper.eq (UserFavorite.FAVORITE_ID, dtoReq.getFavoriteId ())
                .set (StrUtil.isNotBlank (dtoReq.getFavoriteName ()),UserFavorite.FAVORITE_NAME, dtoReq.getFavoriteName ())
                .set (dtoReq.getFavoriteCount () != null, UserFavorite.FAVORITE_COUNT, dtoReq.getFavoriteCount ());
        int update = baseMapper.update (null, wrapper);
        if (update == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public CommonDtoResult<Boolean> delFavorite(Long favoriteId) {
        QueryWrapper<UserFavorite> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserFavorite.FAVORITE_ID, favoriteId);
        int delete = baseMapper.delete (wrapper);
        if (delete == 1) {
            return new CommonDtoResult<> (Boolean.TRUE);
        } else {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getMsg ());
        }
    }

    @Override
    public CommonDtoResult<Integer> getFavoriteCount(Long userId) {
        QueryWrapper<UserFavorite> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserFavorite.USER_ID, userId);
        
        return new CommonDtoResult<> (
                Optional.ofNullable (baseMapper.selectCount (wrapper))
                        .orElse (NumConstant.NUM_0)
        );
    }

    @Override
    public PageDtoResult<FavoriteDtoResult> getFavorites(FavoriteQueryDtoReq dtoReq) {
        QueryWrapper<UserFavorite> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserFavorite.USER_ID, dtoReq.getUserId ());
    
        Page<UserFavorite> values = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize ()),
                wrapper
        );
        
        return PageUtil.pageToPageDtoResult (
                values,
                (o) -> CopyUtils.copySingle (FavoriteDtoResult.class, o)
        );
    }
}
