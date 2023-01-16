package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.extra.CollectionQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FavoriteDetailQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyCollectionDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.CollectionDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserCollectionService;
import com.yixihan.yicode.user.biz.service.extra.UserFavoriteService;
import com.yixihan.yicode.user.dal.mapper.extra.UserCollectionMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserCollection;
import com.yixihan.yicode.user.dal.pojo.extra.UserFavorite;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import javax.annotation.Resource;

/**
 * <p>
 * 用户收藏表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-28
 */
@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {

    @Resource
    private UserFavoriteService favoriteService;
    
    @Override
    @Transactional(rollbackFor = BizException.class)
    public CommonDtoResult<Boolean> addCollection(ModifyCollectionDtoReq dtoReq) {
        // 校验是否重复收藏
        if (baseMapper.selectCount (new QueryWrapper<UserCollection> ()
                .eq (UserCollection.COLLECTION_ID, dtoReq.getCollectionId ())
                .eq (UserCollection.FAVORITE_ID, dtoReq.getFavoriteId ())) > 0) {
            return new CommonDtoResult<> (Boolean.FALSE, "请勿重复收藏内容!");
        }
        
        // 收藏内容
        UserCollection collection = BeanUtil.toBean (dtoReq, UserCollection.class);
        
        int insert = baseMapper.insert (collection);
        if (insert != 1) {
            throw new BizException (BizCodeEnum.FAILED_TYPE_BUSINESS);
        }
        
        // 更新收藏夹
        FavoriteDtoResult favoriteDtoResult = favoriteService.getFavoriteDetail (
                new FavoriteDetailQueryDtoReq (dtoReq.getUserId (), dtoReq.getFavoriteId ()));
        UserFavorite favorite = BeanUtil.toBean (favoriteDtoResult, UserFavorite.class);
        favorite.setFavoriteCount (favorite.getFavoriteCount () + 1);
        
        boolean modify = favoriteService.updateById (favorite);
        if (!modify) {
            throw new BizException (BizCodeEnum.FAILED_TYPE_BUSINESS);
        }
 
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public CommonDtoResult<Boolean> delCollection(ModifyCollectionDtoReq dtoReq) {
        if (verifyFavorite (dtoReq.getUserId (), dtoReq.getFavoriteId ())) {
            return new CommonDtoResult<> (Boolean.FALSE, "该收藏夹不存在或您无权进行操作!");
        }
        
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserCollection.COLLECTION_ID, dtoReq.getCollectionId ())
                .eq (UserCollection.FAVORITE_ID, dtoReq.getFavoriteId ());
        
        int delete = baseMapper.delete (wrapper);
        if (delete != 1) {
            return new CommonDtoResult<> (Boolean.FALSE, BizCodeEnum.FAILED_TYPE_BUSINESS.getErrorMsg ());
        }
        return new CommonDtoResult<> (Boolean.TRUE);
    }

    @Override
    public PageDtoResult<CollectionDtoResult> getCollections(CollectionQueryDtoReq dtoReq) {
        if (verifyFavorite (null, dtoReq.getFavoriteId ())) {
            return new PageDtoResult<> ();
        }
        
        QueryWrapper<UserCollection> wrapper = new QueryWrapper<> ();
        wrapper.eq (UserCollection.FAVORITE_ID, dtoReq.getFavoriteId ());
        
        Page<UserCollection> values = baseMapper.selectPage (
                new Page<> (dtoReq.getPage (), dtoReq.getPageSize ()),
                wrapper
        );
        
        return PageUtil.pageToPageDtoResult (
                values,
                (o) -> CopyUtils.copySingle (CollectionDtoResult.class, o)
        );
    }
    
    /**
     * 判断该收藏夹是否属于该用户 & 判读该收藏夹是否存在
     *
     * @param userId 用户 ID (可为空)
     * @param favoriteId 收藏夹 ID
     * @return 不是：true | 是：false
     */
    private boolean verifyFavorite (@Nullable Long userId, Long favoriteId) {
        QueryWrapper<UserFavorite> wrapper = new QueryWrapper<> ();
        wrapper.eq (userId != null, UserFavorite.USER_ID, userId)
                .eq (UserFavorite.FAVORITE_ID, favoriteId);
        
        return favoriteService.count (wrapper) <= 0;
    }
}
