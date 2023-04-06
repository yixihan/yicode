package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.extra.AddFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FavoriteQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.VerifyFavoriteTypeDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserCollectionService;
import com.yixihan.yicode.user.biz.service.extra.UserFavoriteService;
import com.yixihan.yicode.user.dal.mapper.extra.UserFavoriteMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserFavorite;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    
    @Resource
    private UserCollectionService collectionService;

    @Override
    public FavoriteDtoResult addFavorite(AddFavoriteDtoReq dtoReq) {
        UserFavorite favorite = BeanUtil.toBean (dtoReq, UserFavorite.class);
    
        // 保存
        Assert.isTrue (save (favorite), BizCodeEnum.FAILED_TYPE_BUSINESS);
        
        return BeanUtil.toBean (favorite, FavoriteDtoResult.class);
    }

    @Override
    public FavoriteDtoResult modifyFavorite(ModifyFavoriteDtoReq dtoReq) {
        UserFavorite favorite = BeanUtil.toBean (dtoReq, UserFavorite.class);
        
        // 获取乐观锁
        Integer version = lambdaQuery ()
                .select (UserFavorite::getVersion)
                .eq (UserFavorite::getFavoriteId, dtoReq.getFavoriteId ())
                .one ()
                .getVersion ();
        Assert.notNull (version, new BizException ("该收藏夹不存在"));
        favorite.setVersion (version);
        
        // 更新
        boolean modify = lambdaUpdate ()
                .eq (UserFavorite::getFavoriteId, dtoReq.getFavoriteId ())
                .eq (UserFavorite::getUserId, dtoReq.getUserId ())
                .update (favorite);
        
        Assert.isTrue (modify, BizCodeEnum.FAILED_TYPE_BUSINESS);
        return getFavoriteDetail (dtoReq.getFavoriteId ());
    }

    @Override
    public void delFavorite(Long favoriteId) {
        Assert.isTrue (removeById (favoriteId), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }

    @Override
    public Integer getFavoriteCount(Long userId) {
        return lambdaQuery ()
                .eq (UserFavorite::getUserId, userId)
                .count ();
    }

    @Override
    public PageDtoResult<FavoriteDtoResult> getFavorites(FavoriteQueryDtoReq dtoReq) {
        Page<UserFavorite> page = lambdaQuery ()
                .eq (UserFavorite::getUserId, dtoReq.getUserId ())
                .orderByDesc (UserFavorite::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
        
        return PageUtil.pageToPageDtoResult (
                page,
                o -> BeanUtil.toBean (o, FavoriteDtoResult.class)
        );
    }
    
    @Override
    public FavoriteDtoResult getFavoriteDetail(Long favoriteId) {
        UserFavorite favorite = getById (favoriteId);
        Assert.notNull (favorite, new BizException ("该收藏夹不存在!"));
        
        favorite.setFavoriteCount (collectionService.collectionCount (favoriteId));
        return BeanUtil.toBean (favorite, FavoriteDtoResult.class);
    }
    
    @Override
    public Boolean verifyFavorite(Long favoriteId) {
        return lambdaQuery ()
                .eq (UserFavorite::getFavoriteId, favoriteId)
                .count () > 0;
    }
    
    @Override
    public Boolean verifyFavoriteType(VerifyFavoriteTypeDtoReq dtoReq) {
        return lambdaQuery ()
                .eq (UserFavorite::getFavoriteId, dtoReq.getFavoriteId ())
                .eq (UserFavorite::getFavoriteType, dtoReq.getFavoriteType ())
                .count () > 0;
    }
}
