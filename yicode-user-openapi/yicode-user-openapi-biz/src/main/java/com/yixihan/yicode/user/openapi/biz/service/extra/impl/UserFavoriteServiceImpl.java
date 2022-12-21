package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.AddFavoriteReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.CollectionQueryReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyCollectionReq;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.ModifyFavoriteReq;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.CollectionVO;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FavoriteVO;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserCollectionFeignClient;
import com.yixihan.yicode.user.openapi.biz.feign.user.extra.UserFavoriteFeignClient;
import com.yixihan.yicode.user.openapi.biz.service.base.UserService;
import com.yixihan.yicode.user.openapi.biz.service.extra.UserFavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户收藏 服务实现类
 *
 * @author yixihan
 * @date 2022/12/21 9:45
 */
@Slf4j
@Service
public class UserFavoriteServiceImpl implements UserFavoriteService {
    
    @Resource
    private UserFavoriteFeignClient favoriteFeignClient;
    
    @Resource
    private UserCollectionFeignClient collectionFeignClient;
    
    @Resource
    private UserService userService;
    
    @Override
    public CommonVO<Boolean> addFavorite(AddFavoriteReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> modifyFavorite(ModifyFavoriteReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> delFavorite(Long favoriteId) {
        return null;
    }
    
    @Override
    public CommonVO<Integer> getFavoriteCount(Long userId) {
        return null;
    }
    
    @Override
    public PageVO<FavoriteVO> getFavorites(PageReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> addCollection(ModifyCollectionReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> delCollection(ModifyCollectionReq req) {
        return null;
    }
    
    @Override
    public PageVO<CollectionVO> getCollections(CollectionQueryReq req) {
        return null;
    }
}
