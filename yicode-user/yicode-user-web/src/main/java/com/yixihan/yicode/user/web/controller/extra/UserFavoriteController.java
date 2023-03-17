package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.AddFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FavoriteQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.VerifyFavoriteTypeDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import com.yixihan.yicode.user.api.rest.extra.UserFavoriteApi;
import com.yixihan.yicode.user.biz.service.extra.UserFavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户收藏夹表 前端控制器
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Slf4j
@RestController
public class UserFavoriteController implements UserFavoriteApi {

    @Resource
    private UserFavoriteService service;

    @Override
    public ApiResult<FavoriteDtoResult> addFavorite(AddFavoriteDtoReq dtoReq) {
        return ApiResult.create (service.addFavorite (dtoReq));
    }

    @Override
    public ApiResult<FavoriteDtoResult> modifyFavorite(ModifyFavoriteDtoReq dtoReq) {
        return ApiResult.create (service.modifyFavorite (dtoReq));
    }

    @Override
    public void delFavorite(Long favoriteId) {
        service.delFavorite (favoriteId);
    }

    @Override
    public ApiResult<Integer> getFavoriteCount(Long userId) {
        return ApiResult.create (service.getFavoriteCount (userId));
    }

    @Override
    public ApiResult<PageDtoResult<FavoriteDtoResult>> getFavorites(FavoriteQueryDtoReq dtoReq) {
        return ApiResult.create (service.getFavorites (dtoReq));
    }
    
    @Override
    public ApiResult<FavoriteDtoResult> getFavoriteDetail(Long favoriteId) {
        return ApiResult.create (service.getFavoriteDetail (favoriteId));
    }
    
    @Override
    public ApiResult<Boolean> verifyFavorite(Long favoriteId) {
        return ApiResult.create (service.verifyFavorite (favoriteId));
    }
    
    @Override
    public ApiResult<Boolean> verifyFavoriteType(VerifyFavoriteTypeDtoReq dtoReq) {
        return ApiResult.create (service.verifyFavoriteType (dtoReq));
    }
}
