package com.yixihan.yicode.user.api.rest.extra;


import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.user.api.dto.request.extra.AddFavoriteDtoReq;
import com.yixihan.yicode.user.api.dto.request.extra.FavoriteQueryDtoReq;
import com.yixihan.yicode.user.api.dto.response.extra.FavoriteDtoResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户收藏夹 api
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Api (tags = "用户收藏夹 api")
@RequestMapping("/favorite/")
public interface UserFavoriteApi {

    @ApiOperation("添加收藏夹")
    @PostMapping(value = "/add", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> addFavorite (@RequestBody AddFavoriteDtoReq dtoReq);

    @ApiOperation("修改收藏夹")
    @PostMapping(value = "/modify", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> modifyFavorite (@RequestParam("favoriteId") Long favoriteId,
                                                        @RequestParam("favoriteName") String favoriteName);

    @ApiOperation("删除收藏夹")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> delFavorite (@RequestParam("favoriteId") Long favoriteId);

    @ApiOperation("获取收藏夹数量")
    @PostMapping(value = "/count", produces = "application/json")
    ApiResult<CommonDtoResult<Integer>> getFavoriteCount (@RequestParam("userId") Long userId);

    @ApiOperation("获取所有收藏夹")
    @PostMapping(value = "/del", produces = "application/json")
    ApiResult<PageDtoResult<FavoriteDtoResult>> getFavorites (@RequestBody FavoriteQueryDtoReq dtoReq);

}
