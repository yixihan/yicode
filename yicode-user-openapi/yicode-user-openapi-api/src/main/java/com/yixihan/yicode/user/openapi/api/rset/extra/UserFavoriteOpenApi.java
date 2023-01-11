package com.yixihan.yicode.user.openapi.api.rset.extra;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
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
    JsonResponse<CommonVO<Boolean>> addFavorite (@RequestBody AddFavoriteReq req);
    
    @ApiOperation("修改收藏夹")
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> modifyFavorite (@RequestBody ModifyFavoriteReq req);
    
    @ApiOperation("删除收藏夹")
    @DeleteMapping(value = "/del", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> delFavorite (@RequestParam("favoriteId") Long favoriteId);
    
    @ApiOperation("获取收藏夹数量")
    @GetMapping(value = "/count", produces = "application/json")
    JsonResponse<CommonVO<Integer>> getFavoriteCount (@RequestParam("userId") Long userId);
    
    @ApiOperation("获取所有收藏夹")
    @PostMapping(value = "/detail", produces = "application/json")
    JsonResponse<PageVO<FavoriteVO>> getFavorites (@RequestBody FavoriteQueryReq req);
    
    @ApiOperation ("收藏内容")
    @PostMapping(value = "/collection/add", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> addCollection (@RequestBody ModifyCollectionReq req);
    
    @ApiOperation ("取消收藏内容")
    @DeleteMapping(value = "/collection/del", produces = "application/json")
    JsonResponse<CommonVO<Boolean>> delCollection (@RequestBody ModifyCollectionReq req);
    
    @ApiOperation("获取收藏夹内容")
    @PostMapping(value = "/collection/detail", produces = "application/json")
    JsonResponse<PageVO<CollectionVO>> getCollections(@RequestBody CollectionQueryReq req);
}
