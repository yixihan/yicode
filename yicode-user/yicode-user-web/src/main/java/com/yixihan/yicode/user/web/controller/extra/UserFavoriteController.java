package com.yixihan.yicode.user.web.controller.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.*;
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
    private UserFavoriteService userFavoriteService;

    @Override
    public ApiResult<CommonDtoResult<Boolean>> addFavorite(AddFavoriteDtoReq dtoReq) {
        return ApiResult.create (userFavoriteService.addFavorite (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> modifyFavorite(ModifyFavoriteDtoReq dtoReq) {
        return ApiResult.create (userFavoriteService.modifyFavorite (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Boolean>> delFavorite(ModifyFavoriteDtoReq dtoReq) {
        return ApiResult.create (userFavoriteService.delFavorite (dtoReq));
    }

    @Override
    public ApiResult<CommonDtoResult<Integer>> getFavoriteCount(Long userId) {
        return ApiResult.create (userFavoriteService.getFavoriteCount (userId));
    }

    @Override
    public ApiResult<PageDtoResult<FavoriteDtoResult>> getFavorites(FavoriteQueryDtoReq dtoReq) {
        return ApiResult.create (userFavoriteService.getFavorites (dtoReq));
    }
    
    @Override
    public ApiResult<FavoriteDtoResult> getFavoriteDetail(FavoriteDetailQueryDtoReq dtoReq) {
        return ApiResult.create (userFavoriteService.getFavoriteDetail (dtoReq));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> verifyFavorite(Long favoriteId) {
        return ApiResult.create (userFavoriteService.verifyFavorite (favoriteId));
    }
    
    @Override
    public ApiResult<CommonDtoResult<Boolean>> verifyFavoriteType(VerifyFavoriteTypeDtoReq dtoReq) {
        return ApiResult.create (userFavoriteService.verifyFavoriteType (dtoReq));
    }
}
