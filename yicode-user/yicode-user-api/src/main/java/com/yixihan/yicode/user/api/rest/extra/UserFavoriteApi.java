package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.AddFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FavoriteQueryDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.ModifyFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.VerifyFavoriteTypeDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户收藏夹 api
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Api (tags = "用户收藏夹 api")
@RequestMapping("/favorite")
public interface UserFavoriteApi {

    @ApiOperation("添加收藏夹")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<FavoriteDtoResult> addFavorite (@RequestBody AddFavoriteDtoReq dtoReq);

    @ApiOperation("修改收藏夹")
    @PostMapping(value = "/modify", produces = "application/json")
    ApiResult<FavoriteDtoResult> modifyFavorite (@RequestBody ModifyFavoriteDtoReq dtoReq);

    @ApiOperation("删除收藏夹")
    @PostMapping(value = "/del", produces = "application/json")
    void delFavorite (@RequestBody Long favoriteId);
    
    @ApiOperation("获取收藏夹数量")
    @PostMapping(value = "/count", produces = "application/json")
    ApiResult<Integer> getFavoriteCount (@RequestBody Long userId);
    
    @ApiOperation("获取所有收藏夹")
    @PostMapping(value = "/list", produces = "application/json")
    ApiResult<PageDtoResult<FavoriteDtoResult>> getFavorites (@RequestBody FavoriteQueryDtoReq dtoReq);
    
    @ApiOperation("获取收藏夹详情")
    @PostMapping(value = "/detail", produces = "application/json")
    ApiResult<FavoriteDtoResult> getFavoriteDetail (@RequestBody Long favoriteId);
    
    
    @ApiOperation("校验收藏夹是否存在")
    @PostMapping(value = "/verify/id", produces = "application/json")
    ApiResult<Boolean> verifyFavorite (@RequestBody Long favoriteId);
    
    @ApiOperation("校验收藏夹类型")
    @PostMapping(value = "/verify/type", produces = "application/json")
    ApiResult<Boolean> verifyFavoriteType (@RequestBody VerifyFavoriteTypeDtoReq dtoReq);
}
