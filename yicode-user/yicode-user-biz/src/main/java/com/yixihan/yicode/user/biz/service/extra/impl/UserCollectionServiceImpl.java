package com.yixihan.yicode.user.biz.service.extra.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.common.util.PageUtil;
import com.yixihan.yicode.user.api.dto.request.extra.CollectionQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyCollectionDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.CollectionDtoResult;
import com.yixihan.yicode.user.biz.service.extra.UserCollectionService;
import com.yixihan.yicode.user.biz.service.extra.UserFavoriteService;
import com.yixihan.yicode.user.dal.mapper.extra.UserCollectionMapper;
import com.yixihan.yicode.user.dal.pojo.extra.UserCollection;
import com.yixihan.yicode.user.dal.pojo.extra.UserFavorite;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

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
    public void addCollection(ModifyCollectionDtoReq dtoReq) {
        // 校验是否有操作权限
        Assert.isFalse (Boolean.TRUE.equals (verifyFavorite (dtoReq.getUserId (), dtoReq.getFavoriteId ())),
                new BizException ("该收藏夹不存在或您无权进行操作!"));
        
        // 校验是否重复收藏
        Integer count = lambdaQuery ()
                .eq (UserCollection::getCollectionId, dtoReq.getCollectionId ())
                .eq (UserCollection::getFavoriteId, dtoReq.getFavoriteId ())
                .count ();
        
        Assert.isTrue (count <= 0, new BizException ("请勿重复收藏内容!"));
        
        // 收藏内容
        UserCollection collection = BeanUtil.toBean (dtoReq, UserCollection.class);
        Assert.isTrue (save (collection), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }

    @Override
    @Transactional(rollbackFor = BizException.class)
    public void delCollection(ModifyCollectionDtoReq dtoReq) {
        Assert.isFalse (Boolean.TRUE.equals (verifyFavorite (dtoReq.getUserId (), dtoReq.getFavoriteId ())),
                new BizException ("该收藏夹不存在或您无权进行操作!"));
        
        // 删除
        Long id = lambdaQuery ()
                .select (UserCollection::getId)
                .eq (UserCollection::getFavoriteId, dtoReq.getFavoriteId ())
                .eq (UserCollection::getCollectionId, dtoReq.getCollectionId ())
                .one ()
                .getId ();
        Assert.isTrue (removeById (id), BizCodeEnum.FAILED_TYPE_BUSINESS);
    }

    @Override
    public PageDtoResult<CollectionDtoResult> collectionsDetailPage(CollectionQueryDtoReq dtoReq) {
        Assert.isFalse (Boolean.TRUE.equals (verifyFavorite (null, dtoReq.getFavoriteId ())));
        
        Page<UserCollection> page = lambdaQuery ()
                .eq (UserCollection::getFavoriteId, dtoReq.getFavoriteId ())
                .orderByDesc (UserCollection::getCreateTime)
                .page (PageUtil.toPage (dtoReq));
        
        return PageUtil.pageToPageDtoResult (
                page,
                o -> BeanUtil.toBean (o, CollectionDtoResult.class)
        );
    }
    
    @Override
    public List<CollectionDtoResult> collectionsDetailList(Long favoriteId) {
        List<UserCollection> collectionList = lambdaQuery ()
                .eq (UserCollection::getFavoriteId, favoriteId)
                .orderByDesc (UserCollection::getCreateTime)
                .list ();
        
        collectionList = CollUtil.isEmpty (collectionList) ? Collections.emptyList () : collectionList;
        return BeanUtil.copyToList (collectionList, CollectionDtoResult.class);
    }
    
    @Override
    public Integer collectionCount(Long favoriteId) {
        return lambdaQuery ()
                .eq (UserCollection::getFavoriteId, favoriteId)
                .count ();
    }
    
    /**
     * 判断该收藏夹是否属于该用户 & 判读该收藏夹是否存在
     *
     * @param userId 用户 ID (可为空)
     * @param favoriteId 收藏夹 ID
     * @return 不是:true | 是:false
     */
    private boolean verifyFavorite (@Nullable Long userId, Long favoriteId) {
        return favoriteService.lambdaQuery ()
                .eq (userId != null, UserFavorite::getUserId, userId)
                .eq (UserFavorite::getFavoriteId, favoriteId)
                .count () <= 0;
    }
}
