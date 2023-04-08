package com.yixihan.yicode.user.openapi.api.rset.extra;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.user.openapi.api.vo.request.extra.*;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.CollectionVO;
import com.yixihan.yicode.user.openapi.api.vo.response.extra.FavoriteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户收藏 OpenApi
 *
 * @author yixihan
 * @date 2022/12/21 9:36
 */
@Api(tags = "用户收藏 OpenApi")
@RequestMapping("/open/user/favorite")
public interface UserFavoriteOpenApi {
    
    @ApiOperation("添加收藏夹")
    @PostMapping(value = "/add", produces = "application/json")
    JsonResponse<FavoriteVO> addFavorite (@RequestBody AddFavoriteReq req);
    
    @ApiOperation("修改收藏夹")
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<FavoriteVO> modifyFavorite (@RequestBody ModifyFavoriteReq req);
    
    @ApiOperation("删除收藏夹")
    @DeleteMapping(value = "/del", produces = "application/json")
    void delFavorite (@RequestParam("favoriteId") Long favoriteId);
    
    @ApiOperation("获取收藏夹数量")
    @GetMapping(value = "/count", produces = "application/json")
    JsonResponse<Integer> getFavoriteCount (@RequestParam("userId") Long userId);
    
    @ApiOperation("获取所有收藏夹")
    @PostMapping(value = "/detail", produces = "application/json")
    JsonResponse<PageVO<FavoriteVO>> getFavorites (@RequestBody FavoriteQueryReq req);

    @ApiOperation("获取所有问题收藏夹")
    @PostMapping(value = "/detail/question", produces = "application/json")
    JsonResponse<PageVO<FavoriteVO>> getQuestionFavorites (@RequestBody FavoriteQueryReq req);

    @ApiOperation("获取所有题解收藏夹")
    @PostMapping(value = "/detail/note", produces = "application/json")
    JsonResponse<PageVO<FavoriteVO>> getNoteFavorites (@RequestBody FavoriteQueryReq req);
    
    @ApiOperation("获取收藏夹内容")
    @PostMapping(value = "/collection/detail", produces = "application/json")
    JsonResponse<PageVO<CollectionVO>> getCollections(@RequestBody CollectionQueryReq req);
    
    @ApiOperation("取消收藏")
    @DeleteMapping(value = "/collection/cancel", produces = "application/json")
    void cancel(@RequestBody ModifyCollectionReq id);
}
