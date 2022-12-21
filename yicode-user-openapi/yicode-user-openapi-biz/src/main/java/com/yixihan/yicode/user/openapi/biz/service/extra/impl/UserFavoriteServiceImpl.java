package com.yixihan.yicode.user.openapi.biz.service.extra.impl;

import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.constant.NumConstant;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.CopyUtils;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.user.api.dto.request.extra.*;
import com.yixihan.yicode.user.api.dto.response.extra.CollectionDtoResult;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import com.yixihan.yicode.user.openapi.api.enums.FavoriteTypeEnums;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.*;
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
        // 校验收藏夹名 (不为空) & 收藏夹类型
        if (StrUtil.isBlank (req.getFavoriteName ())) {
            return new CommonVO<> (Boolean.FALSE, "收藏夹不能为空!");
        }
        if (FavoriteTypeEnums.contains (req.getFavoriteType ())) {
            return new CommonVO<> (Boolean.FALSE, "收藏夹类型错误!");
        }
        
        // 新增收藏夹
        AddFavoriteDtoReq dtoReq = CopyUtils.copySingle (AddFavoriteDtoReq.class, req);
        dtoReq.setUserId (userService.getUserInfo ().getUser ().getUserId ());
        CommonDtoResult<Boolean> dtoResult = favoriteFeignClient.addFavorite (dtoReq).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> modifyFavorite(ModifyFavoriteReq req) {
        Long userId = userService.getUserInfo ().getUser ().getUserId ();
        // 校验收藏夹 ID & 收藏夹名 (不为空) & 收藏数量
        if (verifyFavoriteId (userId, req.getFavoriteId ())) {
            return new CommonVO<> (Boolean.FALSE, "收藏夹不存在");
        }
        if (StrUtil.isBlank (req.getFavoriteName ())) {
            return new CommonVO<> (Boolean.FALSE, "收藏夹不能为空!");
        }
        if (req.getFavoriteCount () == null || req.getFavoriteCount () <= NumConstant.NUM_0) {
            return new CommonVO<> (Boolean.FALSE, "收藏数量不能为空或小于零!");
        }
        
        // 修改收藏夹
        ModifyFavoriteDtoReq dtoReq = CopyUtils.copySingle (ModifyFavoriteDtoReq.class, req);
        dtoReq.setUserId (userId);
        CommonDtoResult<Boolean> dtoResult = favoriteFeignClient.modifyFavorite (dtoReq).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delFavorite(Long favoriteId) {
        Long userId = userService.getUserInfo ().getUser ().getUserId ();
        // 校验收藏夹 ID & 收藏夹名 (不为空) & 收藏数量
        if (verifyFavoriteId (userId, favoriteId)) {
            return new CommonVO<> (Boolean.FALSE, "收藏夹不存在");
        }
        
        // 删除收藏夹
        ModifyFavoriteDtoReq dtoReq = new ModifyFavoriteDtoReq ();
        dtoReq.setUserId (userId);
        dtoReq.setFavoriteId (favoriteId);
        CommonDtoResult<Boolean> dtoResult = favoriteFeignClient.delFavorite (dtoReq).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Integer> getFavoriteCount(Long userId) {
        CommonDtoResult<Integer> dtoResult = favoriteFeignClient.getFavoriteCount (userId).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public PageVO<FavoriteVO> getFavorites(FavoriteQueryReq req) {
        FavoriteQueryDtoReq dtoReq = CopyUtils.copySingle (FavoriteQueryDtoReq.class, req);
        dtoReq.setUserId (userService.getUserInfo ().getUser ().getUserId ());

        PageDtoResult<FavoriteDtoResult> dtoResult = favoriteFeignClient.getFavorites (dtoReq).getResult ();
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> CopyUtils.copySingle (FavoriteVO.class, o)
        );
    }
    
    @Override
    public CommonVO<Boolean> addCollection(ModifyCollectionReq req) {
        Long userId = userService.getUserInfo ().getUser ().getUserId ();
        // 校验收藏夹 ID & 收藏内容 ID
        if (verifyFavoriteId (userId, req.getFavoriteId ())) {
            return new CommonVO<> (Boolean.FALSE, "收藏夹不存在");
        }
        // TODO 校验收藏内容 ID
        
        // 收藏内容
        ModifyCollectionDtoReq dtoReq = CopyUtils.copySingle (ModifyCollectionDtoReq.class, req);
        dtoReq.setUserId (userId);
        CommonDtoResult<Boolean> dtoResult = collectionFeignClient.addCollection (dtoReq).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delCollection(ModifyCollectionReq req) {
        Long userId = userService.getUserInfo ().getUser ().getUserId ();
        // 校验收藏夹 ID & 收藏内容 ID
        if (verifyFavoriteId (userId, req.getFavoriteId ())) {
            return new CommonVO<> (Boolean.FALSE, "收藏夹不存在");
        }
        // TODO 校验收藏内容 ID
        
        // 取消收藏
        ModifyCollectionDtoReq dtoReq = CopyUtils.copySingle (ModifyCollectionDtoReq.class, req);
        dtoReq.setUserId (userId);
        CommonDtoResult<Boolean> dtoResult = collectionFeignClient.delCollection (dtoReq).getResult ();
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public PageVO<CollectionVO> getCollections(CollectionQueryReq req) {
        CollectionQueryDtoReq dtoReq = CopyUtils.copySingle (CollectionQueryDtoReq.class, req);
        dtoReq.setUserId (userService.getUserInfo ().getUser ().getUserId ());
    
        PageDtoResult<CollectionDtoResult> dtoResult = collectionFeignClient.getCollections (dtoReq).getResult ();
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                (o) -> CopyUtils.copySingle (CollectionVO.class, o)
        );
    }
    
    /**
     * 校验收藏夹 ID 是否存在
     *
     * @param userId 用户 ID
     * @param favoriteId 收藏夹 ID
     */
    private Boolean verifyFavoriteId (Long userId, Long favoriteId) {
        FavoriteDetailQueryDtoReq dtoReq = new FavoriteDetailQueryDtoReq (userId, favoriteId);
    
        FavoriteDtoResult dtoResult = favoriteFeignClient.getFavoriteDetail (dtoReq).getResult ();
        return dtoResult == null;
    }
}
